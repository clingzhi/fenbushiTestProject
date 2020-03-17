package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.HotelRoom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * >>> 爱旅行-酒店房间功能数据持久层接口
 * 
 */
@Repository
public interface HotelRoomDao {
	/**
	 * >>> 根据酒店ID 查询该酒店的所有房间列表
	 * @param query
	 * @return
	 */
	List<HotelRoom> findHotelRoomListByHotel(HotelRoom query) throws Exception;
	/**
	 * >>> 根据查询条件获得此时的临时库存数量
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer queryTempStore(Map<String, Object> queryMap)throws Exception;
	/**
	 * >>>  根据查询条件查询总库存
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer queryTotalStore(Map<String, Object> queryMap) throws Exception;
}
