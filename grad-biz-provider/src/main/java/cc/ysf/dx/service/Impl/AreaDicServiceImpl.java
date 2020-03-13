package cc.ysf.dx.service.Impl;

import cc.ysf.dx.dao.AreaDicDao;
import cc.ysf.dx.pojo.entity.AreaDic;
import cc.ysf.dx.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * >>> 爱旅行--区域字典信息业务层接口实现类---城市区域地区信息
 *
 *
 *
 */
@Service("areaDicService")
@Transactional
public class AreaDicServiceImpl implements AreaDicService {
	@Autowired
	private AreaDicDao areaDicDao;


	public List<AreaDic> getAreaDicListByQuery(AreaDic query) throws Exception {
		//查询
		List<AreaDic> areaDicList = areaDicDao.findAreaDicListByQuery(query);

		if(areaDicList!=null){
			return areaDicList;
		}
		//如果查询不到返回一个空的集合，防止空指针异常
		return new ArrayList<AreaDic>();
	}
}
