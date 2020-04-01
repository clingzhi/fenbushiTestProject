package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.ItripComment;
import cc.ysf.dx.pojo.entity.ItripImage;
import cc.ysf.dx.pojo.entity.LabelDic;
import cc.ysf.dx.pojo.vo.ItripListCommentVO;
import cc.ysf.dx.pojo.vo.ItripScoreCommentVO;
import cc.ysf.dx.pojo.vo.ItripSearchCommentVO;
import cc.ysf.dx.pojo.vo.Page;
import cc.ysf.dx.service.HotelScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * >>> 爱旅行--传输层接口实现类 酒店房间评分
 */
@RestController("hotelScoreTransport")
@RequestMapping("/comment/trans")
public class HotelScoreTransportImpl implements HotelScoreTransport {
	@Autowired
	private HotelScoreService hotelScoreService;
	/**
	 * >>> 根据条件查询酒店评分
	 * @param query
	 * @return
	 */
	@PostMapping("/query")
	public ItripScoreCommentVO getHotelScore(@RequestBody ItripComment query) throws Exception {
		return hotelScoreService.getHotelScore(query);
	}

	/**
	 * >>> 根据条件查询酒店各种评论数量
	 * @param param
	 * @return
	 */
	@PostMapping("/getcount")
	public Integer getCommentCountByMap(@RequestBody Map<String, Object> param) throws Exception {
		return hotelScoreService.getCommentCountByMap(param);
	}

	/**
	 * >>> 查询评论内容
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getcontent")
	public Page<ItripListCommentVO> getContent(@RequestBody Map<String,Object> paramMap,@RequestParam Integer pageNo,@RequestParam Integer pageSize) throws Exception {
		return hotelScoreService.getContent(paramMap,pageNo,pageSize);
	}

	/**
	 * >>> 添加评论
	 * @param itripComment
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add")
	public Boolean addComment(@RequestBody ItripComment itripComment) throws Exception {
		return hotelScoreService.addComment(itripComment);
	}

	/**
	 * >>> 获取出游类型
	 * @param query
	 * @return
	 */
	@PostMapping("/gettraveltype")
	public List<LabelDic> getTravelType(@RequestBody LabelDic query) throws Exception {
		return hotelScoreService.getTravelType(query);
	}

	/**
	 * ??? 保存图片
	 * @param save
	 * @return
	 */
	@PostMapping("/saveImg")
	public boolean saveImg(@RequestBody ItripImage save) throws Exception {
		return hotelScoreService.saveImg(save);
	}

	/**
	 * >>> 根据ID 获取评论对象
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getcomment")
	public ItripComment getComment(@RequestParam Long orderId) throws Exception {
		return hotelScoreService.getComment(orderId);
	}

}
