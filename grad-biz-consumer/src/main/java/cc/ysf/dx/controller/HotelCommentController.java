package cc.ysf.dx.controller;

import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.enums.ItripImgType;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.ItripComment;
import cc.ysf.dx.pojo.entity.ItripImage;
import cc.ysf.dx.pojo.vo.*;
import cc.ysf.dx.transport.HotelScoreTransport;
import cc.ysf.dx.transport.ItripImagesTransport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController("hotelCommentController")
@RequestMapping("/biz/api/comment")
public class HotelCommentController extends BaseController {
	@Autowired
	private HotelScoreTransport hotelScoreTransport;
	@Autowired
	private ItripImagesTransport itripImagesTransport;


	/**
	 * >>> 查询酒店评分
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/gethotelscore/{hotelId}")
	public ResponseDto<Object> getHotelScore(@PathVariable("hotelId") Long hotelId) throws Exception{
		//查询对象
		ItripComment query = new ItripComment();
		query.setHotelId(hotelId);

		ItripScoreCommentVO itripScoreCommentVO = hotelScoreTransport.getHotelScore(query);
		if (itripScoreCommentVO != null ) {
			return ResponseDto.success(itripScoreCommentVO);
		}
		return ResponseDto.success("还没有评分");
	}

	/**
	 * >>> 根据酒店id查询各类评论数量
	 * @param hotelID
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getcount/{hotelId}")
	public  ResponseDto<Object>  getCount(@PathVariable("hotelId") Long hotelID) throws  Exception{
		//创建返回对象
		Integer count = 0;
		//创建返回集合和查询集合
		Map<String,Integer> countMap = new HashMap<String, Integer>();
		Map<String,Object>  param = new HashMap<String, Object>();

		if( hotelID != null ){
			//查总评论数
			param.put("hotelId", hotelID);
			count = hotelScoreTransport.getCommentCountByMap(param);
			if (count != null && count >0) {
				countMap.put("allcomment", count);
			}else {
				countMap.put("allcomment",0);
			}

			// 查 值得推荐评论数
			param.put("isOk", 1);//1:值得推荐
			count = hotelScoreTransport.getCommentCountByMap(param);
			if (count != null && count >0) {
				countMap.put("isok", count);
			}else {
				countMap.put("isok",0);
			}

			// 查有待提高的评论数
			param.put("isOk", 0);// 0 :有待提高
			count= hotelScoreTransport.getCommentCountByMap(param);
			if (count != null && count >0) {
				countMap.put("improve", count);
			}else {
				countMap.put("improve",0);
			}

			// 查有包含图片的评论
			param.put("isHavingImg", 1);// 1：有图片   0 :没有图片
			count= hotelScoreTransport.getCommentCountByMap(param);
			if (count != null && count >0) {
				countMap.put("havingimg", count);
			}else {
				countMap.put("havingimg",0);
			}

		}
		return ResponseDto.success(countMap);
	}

	/**
	 * >>> 查询评论内容列表 并分页显示
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getcommentlist")
	public ResponseDto<Object> getCommentList(@RequestBody ItripSearchCommentVO itripSearchCommentVO) throws Exception {
		//根据查询视图创建查询集合
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hotelId",itripSearchCommentVO.getHotelId());
		if(itripSearchCommentVO.getIsHavingImg() != -1){
			paramMap.put("isHavingImg",itripSearchCommentVO.getIsHavingImg());
		}
		if (itripSearchCommentVO.getIsOk() != -1) {
			paramMap.put("isOk", itripSearchCommentVO.getIsOk());
		}

		Page<ItripListCommentVO> page = hotelScoreTransport.getContent(paramMap,
				itripSearchCommentVO.getPageNo(),
				itripSearchCommentVO.getPageSize());

		return ResponseDto.success(page);
	}

	/**
	 * >>> 根据targetId查询评论照片(type=2)
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getimg/{targetId}")
	public ResponseDto<Object> getImg(@PathVariable("targetId") Long targetId) throws Exception{
		// 封装查询对象
		ItripImage image = new ItripImage();
		image.setTargetId(targetId);
		image.setType(String.valueOf(ItripImgType.IMG_TYPE_COMMENT.getCode()));

		List<ItripImage> imageList = itripImagesTransport.getImgListByQuery(image);
		return ResponseDto.success(imageList);
	}

	/**
	 * >>> 添加评论
	 * @param itripAddCommentVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add")
	public ResponseDto<Object> addComment(@RequestBody ItripAddCommentVO itripAddCommentVO)throws Exception {
		//创建保存对象
		ItripComment itripComment = new ItripComment();
		BeanUtils.copyProperties(itripAddCommentVO, itripComment);

		Boolean flog = hotelScoreTransport.addComment(itripComment);
		if (flog) {
			return ResponseDto.success("评论成功！");
		}
		return ResponseDto.success("操作失败！");
	}
}
