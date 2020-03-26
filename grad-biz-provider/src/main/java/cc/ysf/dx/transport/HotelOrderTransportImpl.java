package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.HotelOrder;
import cc.ysf.dx.pojo.vo.Page;
import cc.ysf.dx.pojo.vo.RoomStoreVO;
import cc.ysf.dx.pojo.vo.SearchOrderVO;
import cc.ysf.dx.pojo.vo.ValidateRoomStoreVO;
import cc.ysf.dx.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * >>> 传输层接口实现类 查询订单相关信息
 */
@RestController("hotelOrderTransport")
@RequestMapping("/order/trans")
public class HotelOrderTransportImpl implements HotelOrderTransport {
	@Autowired
	private HotelOrderService hotelOrderService;
	/**
	 * >>>   查询下单前的信息
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	public RoomStoreVO getpreorderinfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		return hotelOrderService.getpreorderinfo(validateRoomStoreVO);
	}

	/**
	 * >>> 保存订单
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/saveorder")
	public boolean saveOrder(@RequestBody HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.saveOrder(hotelOrder);
	}

	/**
	 * >>> 使用订单标号查找订单
	 * @param orderNo
	 * @return
	 */
	@PostMapping("/no")
	public HotelOrder getHotelOrderByOrderNo(@RequestParam String orderNo) throws Exception {
		return hotelOrderService.getHotelOrderByOrderNo(orderNo);
	}

	/**
	 * >>> 根据订单ID查找订单
	 * @param ordrId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/id")
	public HotelOrder queryOrderById(@RequestParam Long ordrId) throws Exception {
		return hotelOrderService.getHotelOrderByOrderId(ordrId);
	}

	/**
	 * >>> 查询个人订单列表，并分页显示
	 * @param searchOrderVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/page")
	public Page<HotelOrder> getHotelOrderByPage(@RequestBody SearchOrderVO searchOrderVO) throws Exception {
		return hotelOrderService.getHotelOrderByPage(searchOrderVO);
	}

	/**
	 * >>> 支付完成，修改订单状态
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/updateOrderStatus")
	public boolean updateOrderStatus(HotelOrder order) throws Exception {
		return hotelOrderService.updateOrderStatus(order);
	}
}
