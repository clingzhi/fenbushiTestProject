package cc.ysf.dx.dao;

import org.springframework.stereotype.Repository;

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
}
