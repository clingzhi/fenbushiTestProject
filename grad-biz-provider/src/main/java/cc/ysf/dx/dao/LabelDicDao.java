package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.LabelDic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * >>> 爱旅行--系统典信息数据持久层接口--特色酒店酒店信息
 *
 */
@Repository
public interface LabelDicDao {
	/**
	 *   >>> 根据条件查询用户注册账号是否存在
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> findListByQuery(LabelDic query) throws Exception;
}
