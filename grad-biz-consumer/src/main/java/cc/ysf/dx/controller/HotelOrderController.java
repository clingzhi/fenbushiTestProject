package cc.ysf.dx.controller;

import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.enums.OrderStatusEnum;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.*;
import cc.ysf.dx.pojo.vo.*;
import cc.ysf.dx.transport.HotelOrderTransport;
import cc.ysf.dx.transport.HotelRoomTransprot;
import cc.ysf.dx.transport.UserTransport;
import cc.ysf.dx.util.HotelOrderNoCreator;
import com.sun.org.apache.regexp.internal.RE;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.eac.EACCertificateHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <b>爱旅行-主业务酒店订单模块控制器</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelOrderController")
@RequestMapping("/biz/api/hotelorder")
public class HotelOrderController extends BaseController {
	@Autowired
	private HotelOrderTransport hotelOrderTransport;
	@Autowired
	private HotelRoomTransprot hotelRoomTransprot;
	@Autowired
	private UserTransport userTransport;


	/**
	 * >>> 获取下单先预定信息
	 * @param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getpreorderinfo")
	public ResponseDto<Object> queryPreorder(@RequestBody ValidateRoomStoreVO validateRoomStoreVO)throws Exception{
		// 查询对应日期的房间剩余数量
		RoomStoreVO roomStoreVO = hotelOrderTransport.getpreorderinfo(validateRoomStoreVO);
		return ResponseDto.success(roomStoreVO);
	}

	/**
	 * <b>生成订单</b>
	 * @param addHotelOrderVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addhotelorder")
	public ResponseDto<Object> addHotelOrder(@RequestBody AddHotelOrderVO addHotelOrderVO) throws Exception {

		// 查询此时是否有房，再次查询临时库存
		ValidateRoomStoreVO validateRoomStoreVO = new ValidateRoomStoreVO();
		BeanUtils.copyProperties(addHotelOrderVO, validateRoomStoreVO);

		int store= hotelRoomTransprot.queryHotelRoomByDate(validateRoomStoreVO);
		// 判断库存大于订房数量
		if (store >= validateRoomStoreVO.getCount() ){
			// 保存订单
			String userCode = "";
			Cookie[] cookies =  request.getCookies();
			for (Cookie cookie: cookies) {
				//如果Cookie中token的key为user,取其值作为userCode
				if ("user".equals(cookie.getName())){
					userCode = cookie.getValue();
				}
			}
			//创建USER查询对象
			User userQuery = new User();
			userQuery.setUserCode(userCode);
			//根据账号 找到用户对象
			User user = userTransport.getUserListByUserQuery(userQuery).get(0);
			// 创建订单对象，插入用户ID
			HotelOrder hotelOrder = new HotelOrder();
			hotelOrder.setUserId(user.getId());
			//插入其他的订单包含信息
			BeanUtils.copyProperties(addHotelOrderVO, hotelOrder);
			// 使用工具类生成订单编号
			String orderNo = HotelOrderNoCreator.createHotelOrderNo(hotelOrder.getHotelId(), hotelOrder.getRoomId());
			//订单编号插入
			hotelOrder.setOrderNo(orderNo);
			//交易编号
			hotelOrder.setTradeNo(orderNo);
			//改变订单状态
			hotelOrder.setOrderStatus(OrderStatusEnum.ORDER_STATUS_PREPAY.getCode());
			//找到下单的房间对象
			HotelRoom queryHotelRoom = hotelRoomTransprot.queryHotelRoomById(addHotelOrderVO.getRoomId());
			//预定天数
			Long in = hotelOrder.getCheckInDate().getTime();
			Long out = hotelOrder.getCheckOutDate().getTime();
			Long days =out-in;
			days = days/1000/3600/24;
			System.out.println(days);
			hotelOrder.setBookingDays(Math.toIntExact(days));

			//计算订单价格
			hotelOrder.setPayAmount(addHotelOrderVO.getCount()*queryHotelRoom.getRoomPrice()*hotelOrder.getBookingDays());
			//添加联系人信息
			List<UserLinkUser> userLinkUserList = addHotelOrderVO.getLinkUser();
			StringBuffer sb = new StringBuffer();
			for (UserLinkUser userLinkUser: userLinkUserList) {
				sb.append(userLinkUser.getLinkUserName()+",");
			}
			hotelOrder.setLinkUserName(sb.toString().substring(0, sb.length()-1));

			//保存订单
			hotelOrderTransport.saveOrder(hotelOrder);

			// 保存订单以后，通过订单的ID和订单的订单编号 存Map集合
			HotelOrder order = hotelOrderTransport.getHotelOrderByOrderNo(orderNo);

			Map<String,Object> resultMap = new HashMap<String, Object>();
			resultMap.put("id", order.getId());
			resultMap.put("orderNo", order.getOrderNo());
			// 返回该 Map 集合
			return ResponseDto.success(resultMap);
		}
		return null;
	}

	/**
	 * >>> 根据订单ID查找订单详情
	 * @param ordrId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("queryOrderById/{orderId}")
	public ResponseDto<Object> queryOrderById(@PathVariable("orderId") Long ordrId) throws Exception{

		HotelOrder orders = hotelOrderTransport.queryOrderById(ordrId);
		return ResponseDto.success(orders);
	}

	/**
	 * >>> 根据订单ID查看个人订单详情
	 * @param ordrId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getpersonalorderinfo/{orderId}")
	public ResponseDto<Object> getPersonalOrderInfo(@PathVariable("orderId") Long ordrId) throws Exception{

		HotelOrder orders = hotelOrderTransport.queryOrderById(ordrId);
		return ResponseDto.success(orders);
	}
	/**
	 * >>>  根据订单ID查看个人订单详情-房型相关信息
	 * @param ordrId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getpersonalorderroominfo/{orderId}")
	public ResponseDto<Object> getPersonalOrderroomInfo(@PathVariable("orderId") Long ordrId) throws Exception{

		HotelOrder orders = hotelOrderTransport.queryOrderById(ordrId);
		return ResponseDto.success(orders);
	}

	/**
	 * >>> 查询个人订单列表，并分页显示
	 * @param searchOrderVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getpersonalorderlist")
	public ResponseDto<Object> getPersonalOrderList(@RequestBody SearchOrderVO searchOrderVO)throws Exception{
		//获取当前登录用户信息
		String userCode = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie: cookies) {
			if (cookie.getName().equals("user")){
				userCode=cookie.getValue();
			}
		}
		searchOrderVO.setUserCode(userCode);

			Page<HotelOrder> orderPage = hotelOrderTransport.getHotelOrderByPage(searchOrderVO);
			return ResponseDto.success(orderPage);

	}

}
