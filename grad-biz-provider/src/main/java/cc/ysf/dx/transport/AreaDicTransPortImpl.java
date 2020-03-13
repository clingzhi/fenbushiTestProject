package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.AreaDic;
import cc.ysf.dx.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * >>> 爱旅行--区域字典信息传输层层接口实现类---城市区域地区信息
 */
@RestController("areaDicTransPort")
@RequestMapping("/area/trans")
public class AreaDicTransPortImpl implements AreaDicTransPort {
	@Autowired
	private AreaDicService areaDicService;

	/**
	 * >>>  根据查询条件去数据库查询结果
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/querylist")
	public List<AreaDic> getAreaDicListByQuery(@RequestBody AreaDic query) throws Exception {
		return areaDicService.getAreaDicListByQuery(query);
	}
}
