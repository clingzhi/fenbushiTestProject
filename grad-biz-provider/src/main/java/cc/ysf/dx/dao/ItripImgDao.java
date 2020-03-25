package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.ItripImage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * >>> 爱旅行 --图片数据持久层接口
 */
@Repository
public interface ItripImgDao {

	/**
	 * >>> 根据条件查询 对应图片
	 * @param query
	 * @return
	 */
	List<ItripImage> findListByQuery(ItripImage query) throws Exception;
}
