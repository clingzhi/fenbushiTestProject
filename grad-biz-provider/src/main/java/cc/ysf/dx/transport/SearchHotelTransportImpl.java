package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.Hotel;
import cc.ysf.dx.pojo.vo.HotelVO;
import cc.ysf.dx.pojo.vo.SearchHotCityVO;
import cc.ysf.dx.service.SearchHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
/**
 * >>> 爱旅行--系统典信息传输层接口实现类--热门城市的酒店信息
 */
@RestController("searchHotelTransport")
@RequestMapping("/hotel/trans")
public class SearchHotelTransportImpl implements SearchHotelTransport {
	@Autowired
	private SearchHotelService searchHotelService;
	/**
	 * >>> 根据体检查询酒店列表
	 * @param searchHotCityVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/hotellist")
	public List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO searchHotCityVO) throws Exception {
		return searchHotelService.getHotelListByHotCity(searchHotCityVO);
	}

	/**
	 *  >>>根据主键查询对象信息
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/id")
	public Hotel getHotelById(@RequestParam Long hotelId) throws Exception {
		return searchHotelService.getHotelById(hotelId);
	}

}
