package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.ItripImage;

import java.util.List;
/**
 * >>> 爱旅行-- 业务层接口--查询图片信息
 */
public interface ItripImageService {
	/**
	 * >>> 根据查询条件去数据库查询结果
	 * @param query
	 * @return
	 */
	List<ItripImage> getImgListByQuery(ItripImage query) throws Exception;
}
