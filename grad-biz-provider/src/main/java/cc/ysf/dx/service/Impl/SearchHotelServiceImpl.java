package cc.ysf.dx.service.Impl;

import cc.ysf.dx.dao.SearchHotelDao;
import cc.ysf.dx.pojo.entity.Hotel;
import cc.ysf.dx.pojo.vo.HotelVO;
import cc.ysf.dx.pojo.vo.SearchHotCityVO;
import cc.ysf.dx.service.SearchHotelService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("hotelService")
@Transactional
public class SearchHotelServiceImpl implements SearchHotelService {
	@Autowired
	private SolrClient solrClient;
	@Autowired
	private SearchHotelDao searchHotelDao;

	/**
	 * >>> 根据条件查询热门城市酒店列表
	 * @param searchHotCityVO
	 * @return
	 */
	public List<HotelVO> getHotelListByHotCity(SearchHotCityVO searchHotCityVO) throws Exception {

		// 对于Spring Boot注入的SolrClient就是HttpSolrClient对象，进行强制类型转换
		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		httpSolrClient.setParser(new XMLResponseParser());
		// 创建Solr的查询参数
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("cityId:" + searchHotCityVO.getCityId());
		solrQuery.setRows(searchHotCityVO.getCount());
		// 使用SolrClient进行查询，QueryResponse
		QueryResponse queryResponsey = solrClient.query(solrQuery);
		// 通过使用QueryResponse提取结果
		return queryResponsey.getBeans(HotelVO.class);
	}

	/**
	 *  >>>根据主键查询对象信息
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	public Hotel getHotelById(Long hotelId) throws Exception {
		// 创建查询对象
		Hotel query = new Hotel();
		query.setId(hotelId);
		// 进行列表查询
		List<Hotel> hotelList = searchHotelDao.findListByQuery(query);

		if (hotelList != null && hotelList.size() > 0) {
			return hotelList.get(0);
		}
		return new Hotel();
	}
}
