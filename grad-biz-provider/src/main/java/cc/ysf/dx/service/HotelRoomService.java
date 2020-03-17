package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.pojo.vo.SearchHotelRoomVo;

import java.util.List;

/**
 * >>> 爱旅行-- 业务层接口-- 酒店房间信息
 */
public interface HotelRoomService {
	/**
	 * >>>  根据查询条件去查询酒店房间列表-此刻可以预定的房间列表
	 * @param searchHotelRoomVo
	 * @return
	 * @throws Exception
	 */
	List<HotelRoom> queryHotelRoomByHotel(SearchHotelRoomVo searchHotelRoomVo)throws Exception;
}
