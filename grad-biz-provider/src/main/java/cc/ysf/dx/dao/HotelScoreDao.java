package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.ItripComment;
import cc.ysf.dx.pojo.vo.ItripScoreCommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * >>> 爱旅行-酒店--评分  数据持久层接口
 *
 */
@Repository
public interface HotelScoreDao {
	/**
	 * >>> 根据查询该酒店的 评分
	 * @param query
	 * @return
	 */
	ItripScoreCommentVO findHotelScore(ItripComment query) throws Exception;

}
