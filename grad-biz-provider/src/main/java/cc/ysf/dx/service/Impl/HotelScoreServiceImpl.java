package cc.ysf.dx.service.Impl;


import cc.ysf.dx.dao.HotelCommentDao;
import cc.ysf.dx.dao.HotelScoreDao;
import cc.ysf.dx.pojo.entity.ItripComment;
import cc.ysf.dx.pojo.vo.ItripScoreCommentVO;
import cc.ysf.dx.pojo.vo.ItripSearchCommentVO;
import cc.ysf.dx.pojo.vo.Page;
import cc.ysf.dx.service.HotelScoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("hotelScoreService")
@Transactional
public class HotelScoreServiceImpl implements HotelScoreService {
	@Autowired
	private HotelScoreDao hotelScoreDao;
	@Autowired
	private HotelCommentDao hotelCommentDao;
	/**
	 * >>> 根据条件查询酒店评分
	 * @param query
	 * @return
	 */
	public ItripScoreCommentVO getHotelScore(ItripComment query) throws Exception {
		// 创建返回对象
		ItripScoreCommentVO itripScoreCommentVO = hotelScoreDao.findHotelScore(query);
		if (itripScoreCommentVO != null) {
			//将查询到的Float数据保留一位小数
			DecimalFormat fnum = new DecimalFormat("##0.0");
			itripScoreCommentVO.setAvgFacilitiesScore(Float.parseFloat(fnum.format(itripScoreCommentVO.getAvgFacilitiesScore())));
			itripScoreCommentVO.setAvgHygieneScore(Float.parseFloat(fnum.format(itripScoreCommentVO.getAvgHygieneScore())));
			itripScoreCommentVO.setAvgPositionScore(Float.parseFloat(fnum.format(itripScoreCommentVO.getAvgPositionScore())));
			itripScoreCommentVO.setAvgServiceScore(Float.parseFloat(fnum.format(itripScoreCommentVO.getAvgServiceScore())));
			itripScoreCommentVO.setAvgScore(Float.parseFloat(fnum.format(itripScoreCommentVO.getAvgScore())));

			return itripScoreCommentVO;
		}
		return null;
	}

	/**
	 * >>> 根据条件查询酒店各种评论数量
	 * @param param
	 * @return
	 */
	public Integer getCommentCountByMap(Map<String, Object> param) throws Exception {
		try{
			Integer store = hotelCommentDao.findCommentCount(param);
			return store;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * >>> 查询评论内容,并分页
	 * @return
	 * @throws Exception
	 */
	public Page<ItripComment> getContent(ItripSearchCommentVO itripSearchCommentVO) throws Exception {
		//根据查询视图创建查询集合
		Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("hotelId",itripSearchCommentVO.getHotelId());
			if(itripSearchCommentVO.getIsHavingImg() != -1){
				paramMap.put("isHavingImg",itripSearchCommentVO.getIsHavingImg());
			}
			if (itripSearchCommentVO.getIsOk() != -1) {
				paramMap.put("isOk", itripSearchCommentVO.getIsOk());
			}
			paramMap.put("start", (itripSearchCommentVO.getPageNo() - 1) * itripSearchCommentVO.getPageSize());
			paramMap.put("size", itripSearchCommentVO.getPageSize());
		//引入PQGEHELP
		PageHelper.startPage(itripSearchCommentVO.getPageNo(), itripSearchCommentVO.getPageSize());
		// 获取分页列表
		List<ItripComment> commentList = hotelCommentDao.findCommentContent(paramMap);
		/*
		// 获得总条数
		paramMap.remove("start");
		paramMap.remove("size");
		Integer total =  commentList.size();
		// 封装分页对象
		Page<ItripComment> page = new Page<ItripComment>(itripSearchCommentVO.getPageNo(), itripSearchCommentVO.getPageSize(), total);
		page.setRows(commentList);
		*/
		PageInfo<ItripComment> pageInfo = new PageInfo<>(commentList);
		Page page = new Page();
		page.setCurPage(pageInfo.getPageNum());
		page.setPageSize(pageInfo.getPageSize());
		page.setBeginPos(pageInfo.getStartRow());
		page.setTotal((int) pageInfo.getTotal());
		page.setRows(commentList);
		return page;

	}
}
