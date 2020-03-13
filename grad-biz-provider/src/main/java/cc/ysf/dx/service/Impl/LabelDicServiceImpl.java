package cc.ysf.dx.service.Impl;

import cc.ysf.dx.dao.LabelDicDao;
import cc.ysf.dx.pojo.entity.LabelDic;
import cc.ysf.dx.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * >>> 爱旅行--系统典信息业务层接口实现类--特色酒店酒店信息
 */
@Service("labelDicService")
@Transactional
public class LabelDicServiceImpl implements LabelDicService {
	@Autowired
	private LabelDicDao labelDicDao;

	/**
	 * >>> 根据查询条件去数据库查询结果
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<LabelDic> getListByQuery(LabelDic query) throws Exception {
		//查询
		List<LabelDic> labelDicList = labelDicDao.findListByQuery(query);
		if(labelDicList != null ){
			return labelDicList;
		}
		//如果查询不到返回一个空的集合，防止空指针异常
		return new ArrayList<LabelDic>();
	}
}
