package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.LabelDic;
import cc.ysf.dx.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * >>> 爱旅行--系统典信息传输层接口实现类--特色酒店酒店信息
 */
@RestController("labelDicTransport")
@RequestMapping("/label/trans")
public class LabelDicTransportImpl implements LabelDicTransport {
	@Autowired
	private LabelDicService labelDicService;

	/**
	 * >>>  根据查询条件去数据库查询结果
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/querylist")
	public List<LabelDic> getListByQuery(@RequestBody LabelDic query) throws Exception {
		return labelDicService.getListByQuery(query);
	}
}
