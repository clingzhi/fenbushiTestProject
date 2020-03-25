package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.ItripComment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * >>> 爱旅行-酒店评论（评论数和评论内容)数据持久层接口
 *
 */
@Repository
public interface HotelCommentDao {
	/**
	 * >>> 根据条件查询酒店各种评论数量
	 * @param param
	 * @return
	 */
	Integer findCommentCount(Map<String, Object> param)throws Exception;


	/**
	 * >>> 查询评论内容
	 * @return
	 * @throws Exception
	 */
	List<ItripComment> findCommentContent(Map<String, Object> paramMap)throws Exception;
}
