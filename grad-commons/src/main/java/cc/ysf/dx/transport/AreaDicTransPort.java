package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.AreaDic;
import cc.ysf.dx.pojo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * >>> 爱旅行--区域字典信息传输层层接口---城市区域地区信息
 */
@FeignClient(name = "grad-biz-provider")
@RequestMapping("/area/trans")
public interface AreaDicTransPort {

	/**
	 * >>>  根据查询条件去数据库查询结果
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/querylist")
	List<AreaDic> getAreaDicListByQuery(@RequestBody AreaDic query) throws Exception;

}
