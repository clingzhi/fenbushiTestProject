package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.Hotel;
import cc.ysf.dx.pojo.vo.HotelVO;
import cc.ysf.dx.pojo.vo.SearchHotCityVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * >>> 爱旅行--系统典信息传输层接口--热门城市的酒店信息
 */
@FeignClient(name = "grad-biz-provider")
@RequestMapping("/hotel/trans")
public interface SearchHotelTransport {
	/**
	 * >>> 根据体检查询酒店列表
	 * @param searchHotCityVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/hotellist")
	List<HotelVO> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO)throws  Exception;

	/**
	 *  >>>根据主键查询对象信息
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/id")
	Hotel getHotelById(@RequestParam Long hotelId)throws  Exception;
}
