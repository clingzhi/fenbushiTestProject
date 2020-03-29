package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.ItripComment;
import cc.ysf.dx.pojo.vo.ItripListCommentVO;
import cc.ysf.dx.pojo.vo.ItripScoreCommentVO;
import cc.ysf.dx.pojo.vo.ItripSearchCommentVO;
import cc.ysf.dx.pojo.vo.Page;

import java.util.Map;

/**
 * >>> 爱旅行--业务层接口 酒店房间评分
 */
public interface HotelScoreService {
	/**
	 * >>> 根据条件查询酒店评分
	 * @param query
	 * @return
	 */
	ItripScoreCommentVO getHotelScore(ItripComment query)throws Exception;
	/**
	 * >>> 根据条件查询酒店各种评论数量
	 * @param param
	 * @return
	 */
	Integer getCommentCountByMap(Map<String, Object> param) throws Exception;


	/**
	 * >>> 查询评论内容
	 * @return
	 * @throws Exception
	 */
	Page<ItripListCommentVO> getContent(Map<String, Object> paramMap, Integer pageNo, Integer pageSize)throws Exception;
	/**
	 * >>> 添加评论
	 * @param itripComment
	 * @return
	 * @throws Exception
	 */
	Boolean addComment(ItripComment itripComment)throws Exception;
}
