package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.AreaDic;

import java.util.List;

/**
 * >>> 爱旅行--区域字典信息业务层接口---城市区域地区信息
 *
 */
public interface AreaDicService {
	/**
	 * >>> 根据查询条件去数据库查询结果
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getAreaDicListByQuery(AreaDic query) throws Exception;
}
