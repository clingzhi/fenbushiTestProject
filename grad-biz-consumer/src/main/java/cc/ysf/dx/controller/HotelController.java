package cc.ysf.dx.controller;

import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.enums.AreaHotCityEnum;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.AreaDic;
import cc.ysf.dx.pojo.entity.Hotel;
import cc.ysf.dx.pojo.entity.LabelDic;
import cc.ysf.dx.transport.AreaDicTransPort;
import cc.ysf.dx.transport.LabelDicTransport;
import cc.ysf.dx.transport.SearchHotelTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * >>> 爱旅行 --主业务模块控制层
 */
@RestController("hotelController")
@RequestMapping("/biz/api/hotel")
public class HotelController extends BaseController {
	@Autowired
	private AreaDicTransPort areaDicTransPort;
	@Autowired
	private LabelDicTransport labelDicTransport;
	@Autowired
	private SearchHotelTransport searchHotelTransport;

	/**
	 * >>> 根据条件查询热门酒店列表
	 *
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/queryhotcity/{isChina}")
	// 这个请求查询的是热门城市，{}内的值判断是国内还是国外
	// /xxx/{diy} :diy的值由前端传过来，相似的请求URL，但是可以携带不同的diy值，则取的是不同条件的值
	// 例如:前端穿过来的值是1，在数据库中，属性该字段为1的，则属于国内， 2为国外，
	//    这个值，由前端的两个请求所携带，但是到代码中，由一个请求分发处理，由花括号内字段
	//    作为占位符取值，向方法中传递。
	//                                          @PathVariable("isChina")： 取得占位符的值
	public ResponseDto<Object> queryHotCityList(@PathVariable("isChina") Integer isChina) throws Exception{
		// 创建查询的对象
		AreaDic query = new AreaDic();
			// 判断条件1 ：是国内城市还是国外城市
			query.setIsChina(isChina);
			// 查询条件2 ：是否是热门城市
			query.setIsHot(AreaHotCityEnum.AREA_HOT_YES.getCode());

			//根据条件查询城市List
			List<AreaDic> areaDicList = areaDicTransPort.getAreaDicListByQuery(query);

		return ResponseDto.success(areaDicList);
	}

	/**
	 * >>> 查询包含酒店特色的酒店
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/queryhotelfeature")
	public ResponseDto<Object> queryHotelFeature() throws Exception {
		// 创建查询对象
		LabelDic query = new LabelDic();
		query.setParentId(16L);

		List<LabelDic> labelDicList = labelDicTransport.getListByQuery(query);

		return ResponseDto.success(labelDicList);
	}

	/**
	 * <b>根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getvideodesc/{hotelId}")
	public ResponseDto<Object> getVideoDesc(@PathVariable("hotelId") Long hotelId) throws Exception {
		Hotel hotel = searchHotelTransport.getHotelById(hotelId);
		return ResponseDto.success(hotel);
	}
}
