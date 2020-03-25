package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.pojo.vo.SearchHotelRoomVo;
import cc.ysf.dx.pojo.vo.ValidateRoomStoreVO;
import cc.ysf.dx.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	/**
	 * >>> 下单时，再次查询临时库存
	 * @param validateRoomStoreVO
	 * @return
	 */
	@PostMapping("/querystore")
	public int queryHotelRoomByDate(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		return hotelRoomService.getHotelRoomByDate(validateRoomStoreVO);
	}

	/**
	 * >>>  查询就点房间用房间ID查
	 * @param roomId
	 * @return
	 */
	@PostMapping("/id")
	public HotelRoom  queryHotelRoomById(@RequestParam Long roomId)throws Exception {
		return hotelRoomService.getHotelRoomById(roomId);
	}


}
