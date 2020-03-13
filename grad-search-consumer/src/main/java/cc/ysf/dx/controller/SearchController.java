package cc.ysf.dx.controller;

import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.vo.HotelVO;
import cc.ysf.dx.pojo.vo.SearchHotCityVO;
import cc.ysf.dx.transport.SearchHotelTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * >>> 爱旅行--搜索模块控制器
 *
 */

@RestController("searchController")
@RequestMapping("/search/api")
public class SearchController extends BaseController {

	@Autowired
	private SearchHotelTransport searchHotelTransport;


	/**
	 * >>> 根据条件搜索热门城市酒店
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/hotellist/searchItripHotelListByHotCity")
	public ResponseDto<Object> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO)throws Exception{

		List<HotelVO> hotelList = searchHotelTransport.searchItripHotelListByHotCity(searchHotCityVO);
		return ResponseDto.success(hotelList);
	}
}
