package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.HotelOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * >>> 爱旅行-酒店房间订单数据持久层接口
 *
 */
@Repository
public interface HotelOrderDao {
	/**
	 * <b>根据查询条件查询未支付和已支付的订单中所下单的房间总数</b>
	 * @param orderQueryMap
	 * @return
	 * @throws Exception
	 */
	Integer findOrderRoomCountByQuery(Map<String, Object> orderQueryMap) throws Exception;
	/**
	 * >>> 保存订单
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	int saveOrder(HotelOrder hotelOrder)throws Exception;

	/**
	 * >>> 根据条件查询订单
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder>   findOrderListByQuery(HotelOrder query)throws Exception;
	/**
	 * >>> 查询个人订单列表，并分页显示
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder> findOrderListByPage(Map<String, Object> query)throws Exception;
	/**
	 * >>> 支付完成，修改订单状态
	 * @param order
	 * @return
	 * @throws Exception
	 */
	int updateOrderStatus(HotelOrder order)throws Exception;
}
