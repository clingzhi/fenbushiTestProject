package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.pojo.vo.SearchHotelRoomVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * >>> 爱旅行--传输层接口 酒店房间信息
 */
@FeignClient(name = "grad-biz-provider")
@RequestMapping("/hotelroom/trans")
public interface HotelRoomTransprot {
	/**
	 * >>>  查询酒店房间列表-此刻可以预定的房间列表
	 * @param searchHotelRoomVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryhotelroom")
	List<HotelRoom> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVo searchHotelRoomVo) throws Exception;
}
