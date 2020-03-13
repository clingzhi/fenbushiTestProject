package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.AreaDic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * >>> 爱旅行--区域字典信息数据持久层接口---城市区域地区信息
 *
 */
@Repository
public interface AreaDicDao {
	/**
	 *   >>> 根据条件查询用户注册账号是否存在
	 * @param query
	 * @return
	 * @throws Exception
	 */
	 List<AreaDic> findAreaDicListByQuery(AreaDic query) throws Exception;
}
