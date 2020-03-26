package cc.ysf.dx.service.Impl;

import cc.ysf.dx.dao.HotelOrderDao;
import cc.ysf.dx.dao.HotelRoomDao;
import cc.ysf.dx.dao.SearchHotelDao;
import cc.ysf.dx.pojo.entity.Hotel;
import cc.ysf.dx.pojo.entity.HotelOrder;
import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.pojo.vo.Page;
import cc.ysf.dx.pojo.vo.RoomStoreVO;
import cc.ysf.dx.pojo.vo.SearchOrderVO;
import cc.ysf.dx.pojo.vo.ValidateRoomStoreVO;
import cc.ysf.dx.service.HotelOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * >>> 爱旅行-- 业务层接口-- 下单需要信息
 */
@Service("hotelOrderService")
@Transactional
public class HotelOrderServiceImpl implements HotelOrderService {
	@Autowired
	private HotelOrderDao hotelOrderDao;
	@Autowired
	private HotelRoomDao hotelRoomDao;
	@Autowired
	private SearchHotelDao searchHotelDao;

	/**
	 * >>>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	public RoomStoreVO getpreorderinfo(ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		//创建返回对象
		RoomStoreVO roomStoreVO = new  RoomStoreVO();

		//插入入离时间
		roomStoreVO.setCheckInDate(validateRoomStoreVO.getCheckInDate());
		roomStoreVO.setCheckOutDate(validateRoomStoreVO.getCheckOutDate());
		//插入定房间数量
		roomStoreVO.setCount(validateRoomStoreVO.getCount());

		// 根据酒店ID 查询酒店对象，
		Hotel hotel = new Hotel();
		hotel.setId(validateRoomStoreVO.getHotelId());
		List<Hotel> hotellist = searchHotelDao.findListByQuery(hotel);
			//存入酒店ID和酒店名
			roomStoreVO.setHotelId(hotellist.get(0).getId());
			roomStoreVO.setHotelName(hotellist.get(0).getHotelName());

		//根据HotelroomId查询酒店房间价格
		HotelRoom query = new HotelRoom();
		query.setId(validateRoomStoreVO.getRoomId());
		query.setHotelId(validateRoomStoreVO.getHotelId());
		List<HotelRoom> HotelRoomList = hotelRoomDao.findHotelRoomList(query);
			//存入酒店房间ID和价格
			roomStoreVO.setRoomId(HotelRoomList.get(0).getId());
			roomStoreVO.setPrice(HotelRoomList.get(0).getRoomPrice());


		// 查询临时库存，根据酒店ID，房间ID，开始时间，封装查询数据去查询
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", validateRoomStoreVO.getHotelId());
		queryMap.put("roomId", validateRoomStoreVO.getRoomId() );
		queryMap.put("beginDate",validateRoomStoreVO.getCheckInDate());

		Integer store = hotelRoomDao.queryTempStore(queryMap);

		if(store == null){
			//如果临时库存不存在 ，就查询总库存数量
			queryMap.put("productId", validateRoomStoreVO.getRoomId());
			store = hotelRoomDao.queryTotalStore(queryMap);
		}

		//计算可用库存，如果大于零
		if(store != null && store > 0){
			//在此计算时，订单表中下单和已经支付的订单使用的库存
			Map<String,Object> orderQueryMap = new HashMap<String, Object>();
			orderQueryMap.put("roomId", validateRoomStoreVO.getRoomId());
			orderQueryMap.put("startDate",validateRoomStoreVO.getCheckInDate());
			orderQueryMap.put("endDate", validateRoomStoreVO.getCheckOutDate());

			Integer orderRoomCount = hotelOrderDao.findOrderRoomCountByQuery(orderQueryMap);

			// 用库存减去订单中的小号 存入返回VO对象
			roomStoreVO.setStore(store-orderRoomCount);
		}
		return roomStoreVO;
	}

	/**
	 * >>> 保存订单
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	public boolean saveOrder(HotelOrder hotelOrder) throws Exception {
		int count = hotelOrderDao.saveOrder(hotelOrder);
		if (count>0){
			return true;
		}
		return false;
	}

	/**
	 * >>> 使用订单标号查找订单
	 * @param orderNo
	 * @return
	 */
	public HotelOrder getHotelOrderByOrderNo(String orderNo) throws Exception {
		HotelOrder query = new HotelOrder();
		query.setOrderNo(orderNo);

		List<HotelOrder> hotelOrders = hotelOrderDao.findOrderListByQuery(query);
		if (hotelOrders != null) {
			return hotelOrders.get(0);
		}
		return null;
	}

	/**
	 * >>> 根据订单ID查找订单
	 * @param ordrId
	 * @return
	 * @throws Exception
	 */
	public HotelOrder getHotelOrderByOrderId(Long ordrId) throws Exception {
		HotelOrder query = new HotelOrder();
		query.setId(ordrId);

		List<HotelOrder> orders = hotelOrderDao.findOrderListByQuery(query);
		if (orders != null && orders.size()>0){
			return orders.get(0);
		}
		return null;
	}

	/**
	 * >>> 查询个人订单列表，并分页显示
	 * @param searchOrderVO
	 * @return
	 * @throws Exception
	 */
	public Page<HotelOrder> getHotelOrderByPage(SearchOrderVO searchOrderVO) throws Exception {
		//空字符判断
		if("".equals(searchOrderVO.getOrderNo())){
			searchOrderVO.setOrderNo(null);
		}
		if("".equals(searchOrderVO.getLinkUserName())){
			searchOrderVO.setLinkUserName(null);
		}
		if ("".equals(searchOrderVO.getStartDate())){
			searchOrderVO.setStartDate(null);
		}
		if ("".equals(searchOrderVO.getEndDate())){
			searchOrderVO.setEndDate(null);
		}
		//封装查询Map
		Map<String,Object> query = new HashMap<>();
		query.put("orderNo",searchOrderVO.getOrderNo());
		query.put("linkUserName",searchOrderVO.getLinkUserName());
		query.put("startDate",searchOrderVO.getStartDate());
		query.put("endDate",searchOrderVO.getEndDate());
		query.put("pageSize",searchOrderVO.getPageSize());
		query.put("pageNo",searchOrderVO.getPageSize());
		query.put("orderStatus",searchOrderVO.getOrderStatus());
		query.put("orderType",searchOrderVO.getOrderType());
		query.put("userCode",searchOrderVO.getUserCode());

		PageHelper.startPage(searchOrderVO.getPageNo(), searchOrderVO.getPageSize());
		List<HotelOrder> orderList = hotelOrderDao.findOrderListByPage(query);

		PageInfo<HotelOrder> pageInfo = new PageInfo<>(orderList);
		Page page =new Page();
		page.setCurPage(pageInfo.getPageNum());//页码
		page.setTotal((int) pageInfo.getTotal()); //总记录
		page.setPageSize(pageInfo.getPageSize());
		page.setBeginPos(pageInfo.getStartRow());// 结果集中数据的起始位置  .
		page.setRows(orderList);//List集合

		return page;
	}

	/**
	 * >>> 支付完成，修改订单状态
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderStatus(HotelOrder order) throws Exception {
		//设定支付时间
		order.setCheckInDate(new Date());
		//进行更新
		int flog = hotelOrderDao.updateOrderStatus(order);
		if (flog>0){
			return true;
		}
		return false;
	}
}