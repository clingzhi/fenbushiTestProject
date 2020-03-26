package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.HotelOrder;
import cc.ysf.dx.pojo.vo.Page;
import cc.ysf.dx.pojo.vo.RoomStoreVO;
import cc.ysf.dx.pojo.vo.SearchOrderVO;
import cc.ysf.dx.pojo.vo.ValidateRoomStoreVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

/**
 * >>> 传输层接口 查询订单相关信息
 */
@FeignClient("grad-biz-provider")
@RequestMapping("/order/trans")
public interface HotelOrderTransport {
	/**
	 * >>>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	RoomStoreVO getpreorderinfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO)throws Exception;

	/**
	 * >>> 保存订单
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/saveorder")
	boolean saveOrder(@RequestBody HotelOrder hotelOrder) throws Exception;

	/**
	 * >>> 使用订单标号查找订单
	 * @param orderNo
	 * @return
	 */
	@PostMapping("/no")
	HotelOrder getHotelOrderByOrderNo(@RequestParam String orderNo) throws Exception;
	/**
	 * >>> 根据订单ID查找订单
	 * @param ordrId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/id")
	HotelOrder queryOrderById(@RequestParam Long ordrId)throws Exception;
	/**
	 * >>> 查询个人订单列表，并分页显示
	 * @param searchOrderVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/page")
	Page<HotelOrder> getHotelOrderByPage(@RequestBody SearchOrderVO searchOrderVO)throws Exception;
	/**
	 * >>> 支付完成，修改订单状态
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/updateOrderStatus")
	boolean updateOrderStatus(@RequestBody HotelOrder order)throws Exception;
}
