package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.LabelDic;

import java.util.List;

/**
 * >>> 爱旅行--系统典信息业务层接口--特色酒店酒店信息
 */
public interface LabelDicService {
	/**
	 * >>>  根据查询条件去数据库查询结果
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> getListByQuery(LabelDic query) throws Exception;
}
