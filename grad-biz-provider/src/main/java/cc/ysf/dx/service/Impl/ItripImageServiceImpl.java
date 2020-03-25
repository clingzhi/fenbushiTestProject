package cc.ysf.dx.service.Impl;

import cc.ysf.dx.dao.ItripImgDao;
import cc.ysf.dx.pojo.entity.ItripImage;
import cc.ysf.dx.service.ItripImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * >>> 爱旅行-- 业务层接口实现类--查询图片信息
 */
@Service("itripImageService")
@Transactional
public class ItripImageServiceImpl implements ItripImageService {
	@Autowired
	private ItripImgDao itripImgDao;
	/**
	 * >>> 根据查询条件去数据库查询结果
	 * @param query
	 * @return
	 */
	public List<ItripImage> getImgListByQuery(ItripImage query) throws Exception {
		List<ItripImage> itripImageList = itripImgDao.findListByQuery(query);

		if(itripImageList != null){
			return  itripImageList;
		}
		return new ArrayList<ItripImage>();
	}
}
