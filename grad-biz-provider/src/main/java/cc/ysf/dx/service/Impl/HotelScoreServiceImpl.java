package cc.ysf.dx.service.Impl;


import cc.ysf.dx.dao.*;
import cc.ysf.dx.pojo.entity.Hotel;
import cc.ysf.dx.pojo.entity.HotelOrder;
import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.pojo.entity.ItripComment;
import cc.ysf.dx.pojo.vo.ItripListCommentVO;
import cc.ysf.dx.pojo.vo.ItripScoreCommentVO;
import cc.ysf.dx.pojo.vo.ItripSearchCommentVO;
import cc.ysf.dx.pojo.vo.Page;
import cc.ysf.dx.service.HotelScoreService;
import cc.ysf.dx.transport.HotelOrderTransport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

@Service("hotelScoreService")
@Transactional
public class HotelScoreServiceImpl implements HotelScoreService {
	@Autowired
	private HotelScoreDao hotelScoreDao;
	@Autowired
	private HotelCommentDao hotelCommentDao;
	@Autowired
	private SearchHotelDao searchHotelDao;
	@Autowired
	private HotelOrderDao hotelOrderDao;
	@Autowired
	private HotelRoomDao hotelRoomDao;
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
			Integer store = hotelCommentDao.findCommentCount(param);
			return store;
	}

	/**
	 * >>> 查询评论内容,并分页
	 * @return
	 * @throws Exception
	 */
	public Page<ItripListCommentVO> getContent(Map<String, Object> paramMap,
	                                           Integer pageNo, Integer pageSize) throws Exception {
		paramMap.put("start", (pageNo - 1) * pageSize);
		paramMap.put("size", pageSize);
		//引入PQGEHELP
		PageHelper.startPage(pageNo,pageSize);
		// 获取评论内容列表
		List<ItripComment> commentList = hotelCommentDao.findCommentContent(paramMap);

		/*
		//创建评论返回集合
		List<ItripListCommentVO> itripListCommentVOS = new ArrayList<>();

		for (ItripComment itripCommens: commentList) {
			//创建返回的VO对象
			ItripListCommentVO itripListCommentVO = new ItripListCommentVO();
			//存入酒店星级，酒店特色类型，入住时间等信息
			itripListCommentVO.setId(itripCommens.getId());
				//创建酒店对象 查找酒店等级
				Hotel queryHotel = new Hotel();
				queryHotel.setId(itripCommens.getHotelId());
				List<Hotel> hotels = searchHotelDao.findListByQuery(queryHotel);
					for (Hotel hotel: hotels) {
						itripListCommentVO.setHotelLevel(hotel.getHotelLevel());
					}
				//创建订单对象 查找订单入住时间
				HotelOrder queryOrder = new HotelOrder();
				queryOrder.setId(itripCommens.getOrderId());
				List<HotelOrder> hotelOrders = hotelOrderDao.findOrderListByQuery(queryOrder);
					for (HotelOrder hotelOrder:hotelOrders) {
						itripListCommentVO.setCheckInDate(hotelOrder.getCheckInDate());
						itripListCommentVO.setUserCode(hotelOrder.getLinkUserName());//评论的用户名字
					}
				//创建房间对象 查找房间类型
				HotelRoom queryRoom = new HotelRoom();
				queryRoom.setId(itripCommens.getProductId());
				List<HotelRoom> rooms = hotelRoomDao.findHotelRoomList(queryRoom);
					for (HotelRoom hotelRoom:rooms) {
						itripListCommentVO.setRoomTitle(hotelRoom.getRoomTitle());
					}
			itripListCommentVO.setTripModeName(itripCommens.getTripMode());
			itripListCommentVO.setContent(itripCommens.getContent());
			itripListCommentVO.setCreationDate(itripCommens.getCreationDate());
			itripListCommentVO.setScore(itripCommens.getScore());
			itripListCommentVO.setIsHavingImg(itripCommens.getIsHavingImg());
			//将返回对象添加进返回集合
			itripListCommentVOS.add(itripListCommentVO);
		}*/

		PageInfo<ItripComment> pageInfo = new PageInfo<ItripComment>(commentList);
		Page page = new Page();
		page.setCurPage(pageInfo.getPageNum());
		page.setPageSize(pageInfo.getPageSize());
		page.setBeginPos(pageInfo.getStartRow());
		page.setTotal((int) pageInfo.getTotal());
		page.setRows(commentList);

		return page;

		}

	/**
	 * >>> 添加评论
	 * @param itripComment
	 * @return
	 * @throws Exception
	 */
	public Boolean addComment(ItripComment itripComment) throws Exception {
		HotelOrder query = new HotelOrder();
		query.setId(itripComment.getOrderId());
		List<HotelOrder> orders = hotelOrderDao.findOrderListByQuery(query);

		itripComment.setUserId(orders.get(0).getUserId());
		itripComment.setCreationDate(new Date());
		Integer ALLscore = itripComment.getFacilitiesScore()+itripComment.getHygieneScore()+
				itripComment.getPositionScore()+itripComment.getServiceScore();
		itripComment.setScore(ALLscore/4);

		Boolean flog = hotelCommentDao.addComment(itripComment);
		if (flog){
			return true;
		}
		return false;
	}
}
