package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.ItripComment;
import cc.ysf.dx.pojo.vo.ItripScoreCommentVO;
import cc.ysf.dx.pojo.vo.ItripSearchCommentVO;
import cc.ysf.dx.pojo.vo.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * >>> 爱旅行--传输层接口 酒店房间评分
 */
@FeignClient("grad-biz-provider")
@RequestMapping("/comment/trans")
public interface HotelScoreTransport {
	/**
	 * >>> 根据条件查询酒店评分
	 * @param query
	 * @return
	 */
	@PostMapping("/query")
	ItripScoreCommentVO getHotelScore(@RequestBody ItripComment query) throws Exception;

	/**
	 * >>> 根据条件查询酒店各种评论数量
	 * @param param
	 * @return
	 */
	@PostMapping("/getcount")
	Integer getCommentCountByMap(@RequestBody Map<String, Object> param) throws Exception;
	/**
	 * >>> 查询评论内容
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getcontent")
	Page<ItripComment> getContent(@RequestBody ItripSearchCommentVO itripSearchCommentVO) throws Exception;
}
