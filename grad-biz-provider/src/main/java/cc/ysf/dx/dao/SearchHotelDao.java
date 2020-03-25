package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * >>> 爱旅行--系统典信息数据持久层接口--热门城市酒店信息
 *
 */
@Repository
public interface SearchHotelDao {

	/**
	 * >>> 根据条件查询城市酒店列表
	 *
	 * @param query
	 * @return
	 */
	 List<Hotel> findListByQuery(Hotel query) throws Exception ;

}
