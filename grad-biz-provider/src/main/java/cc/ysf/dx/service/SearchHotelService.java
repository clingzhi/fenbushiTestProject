package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.Hotel;
import cc.ysf.dx.pojo.vo.HotelVO;
import cc.ysf.dx.pojo.vo.SearchHotCityVO;

import java.util.List;

/**
 * >>> 爱旅行--系统典信息业务层接口--热门城市酒店列表
 */
public interface SearchHotelService {
	/**
	 * >>> 根据条件查询热门城市酒店列表
	 * @param searchHotCityVO
	 * @return
	 */
	List<HotelVO> getHotelListByHotCity(SearchHotCityVO searchHotCityVO) throws Exception;

	/**
	 *  >>>根据主键查询对象信息
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	Hotel getHotelById(Long hotelId)throws Exception;
}
