package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.pojo.vo.SearchHotelRoomVo;
import cc.ysf.dx.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * >>> 爱旅行--传输层接口实现类 酒店房间信息
 */

@RestController("hotelRoomTransprot")
@RequestMapping("/hotelroom/trans")
public class HotelRoomTransprotImpl implements HotelRoomTransprot {
	@Autowired
	private HotelRoomService hotelRoomService;
	/**
	 * >>>  查询酒店房间列表-此刻可以预定的房间列表
	 * @param searchHotelRoomVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryhotelroom")
	public List<HotelRoom> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVo searchHotelRoomVo) throws Exception {
		return hotelRoomService.queryHotelRoomByHotel(searchHotelRoomVo);
	}
}
