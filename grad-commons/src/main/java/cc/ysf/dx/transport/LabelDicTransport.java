package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.LabelDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * >>> 爱旅行--系统典信息传输层接口--特色酒店酒店信息
 */
@FeignClient(name = "grad-biz-provider")
@RequestMapping("/label/trans")
public interface LabelDicTransport {

	/**
	 * >>>  根据查询条件去数据库查询结果
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/querylist")
	List<LabelDic> getListByQuery(@RequestBody LabelDic query) throws Exception;
}
