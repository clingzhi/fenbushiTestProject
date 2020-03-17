package cc.ysf.dx.controller;

import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.pojo.entity.LabelDic;
import cc.ysf.dx.pojo.vo.SearchHotelRoomVo;
import cc.ysf.dx.transport.HotelRoomTransprot;
import cc.ysf.dx.transport.LabelDicTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * >>> 爱旅行 -- 酒店房间控制模块
 */
@RestController("hotelRoomController")
@RequestMapping("/biz/api/hotelroom")
public class HotelRoomController extends BaseController {
	@Autowired
	private HotelRoomTransprot hotelRoomTransprot;
	@Autowired
	private LabelDicTransport labelDicTransport;
	/**
	 * >>> 查询酒店房间列表-此刻可以预定的房间列表
	 * @param searchHotelRoomVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryhotelroombyhotel")
	public ResponseDto<Object> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVo searchHotelRoomVo) throws Exception{
		List<List<HotelRoom>> resultList = new ArrayList<List<HotelRoom>>();
		//查询可用房间列表
		List<HotelRoom> hotelRoomList = hotelRoomTransprot.queryHotelRoomByHotel(searchHotelRoomVo);

		// 循环遍历集合
		if (hotelRoomList != null && hotelRoomList.size() > 0) {
			for (HotelRoom hotelRoom : hotelRoomList) {
				List<HotelRoom> list = new ArrayList<HotelRoom>();
				list.add(hotelRoom);
				resultList.add(list);
			}
		}
		return ResponseDto.success(resultList);
	}

	/**
	 * >>>查询酒店房间床型列表
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/queryhotelroombed")
	public ResponseDto<Object> queryHotelRoomBed()throws Exception{
		// 封装查询对象
		LabelDic query =new LabelDic();
		//查询所有床型，根据一级菜单ID
		query.setParentId(1L);
		//床型列表
		List<LabelDic>  labelDicList = labelDicTransport.getListByQuery(query);
		return ResponseDto.success(labelDicList);
	}
}
