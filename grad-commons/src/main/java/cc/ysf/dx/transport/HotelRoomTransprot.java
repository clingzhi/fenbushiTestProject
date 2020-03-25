package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.pojo.vo.SearchHotelRoomVo;
import cc.ysf.dx.pojo.vo.ValidateRoomStoreVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * >>> 爱旅行--传输层接口 酒店房间信息
 */
@FeignClient(name = "grad-biz-provider")
@RequestMapping("/hotelroom/trans")
public interface HotelRoomTransprot {
	/**
	 * >>>  查询酒店房间列表-此刻可以预定的房间列表--用酒店查
	 * @param searchHotelRoomVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryhotelroom")
	List<HotelRoom> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVo searchHotelRoomVo) throws Exception;

	/**
	 * >>> 用时间查询酒店房间 ----下单时，再次查询临时库存
	 * @param validateRoomStoreVO
	 * @return
	 */
	@PostMapping("/querystore")
	int queryHotelRoomByDate(@RequestBody  ValidateRoomStoreVO validateRoomStoreVO)throws Exception;

	/**
	 * >>>  查询就点房间用房间ID查
	 * @param roomId
	 * @return
	 */
	@PostMapping("/id")
	HotelRoom  queryHotelRoomById(@RequestParam Long roomId)throws Exception;
}
