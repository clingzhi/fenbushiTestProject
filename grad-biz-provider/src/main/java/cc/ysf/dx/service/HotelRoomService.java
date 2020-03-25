package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.pojo.vo.SearchHotelRoomVo;
import cc.ysf.dx.pojo.vo.ValidateRoomStoreVO;
import org.springframework.web.bind.annotation.RequestBody;

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
	/**
	 * >>> 下单时，再次查询临时库存
	 * @param validateRoomStoreVO
	 * @return
	 */
	int getHotelRoomByDate(ValidateRoomStoreVO validateRoomStoreVO) throws Exception;
	/**
	 * >>>  查询就点房间用房间ID查
	 * @param roomId
	 * @return
	 */
	HotelRoom  getHotelRoomById(Long roomId) throws Exception;
}
