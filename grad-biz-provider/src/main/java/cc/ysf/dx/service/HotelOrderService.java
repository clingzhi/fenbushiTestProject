package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.HotelOrder;
import cc.ysf.dx.pojo.vo.Page;
import cc.ysf.dx.pojo.vo.RoomStoreVO;
import cc.ysf.dx.pojo.vo.SearchOrderVO;
import cc.ysf.dx.pojo.vo.ValidateRoomStoreVO;
/**
 * >>> 爱旅行-- 业务层接口-- 下单需要信息
 */
public interface HotelOrderService {
	/**
	 * >>>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	RoomStoreVO getpreorderinfo(ValidateRoomStoreVO validateRoomStoreVO) throws Exception;
	/**
	 * >>> 保存订单
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	boolean saveOrder(HotelOrder hotelOrder)throws Exception;
	/**
	 * >>> 使用订单标号查找订单
	 * @param orderNo
	 * @return
	 */
	HotelOrder getHotelOrderByOrderNo(String orderNo)throws Exception;
	/**
	 * >>> 根据订单ID查找订单
	 * @param ordrId
	 * @return
	 * @throws Exception
	 */
	HotelOrder getHotelOrderByOrderId(Long ordrId)throws Exception;
	/**
	 * >>> 查询个人订单列表，并分页显示
	 * @param searchOrderVO
	 * @return
	 * @throws Exception
	 */
	Page<HotelOrder> getHotelOrderByPage(SearchOrderVO searchOrderVO)throws Exception;
}
