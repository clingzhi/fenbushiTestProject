package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.ItripImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * >>> 爱旅行--传输层接口 图片
 */
@FeignClient(name ="grad-biz-provider")
@RequestMapping("/img/trans")
public interface ItripImagesTransport {
	/**
	 * >>> 根据查询条件查询
	 * @param query
	 * @return
	 */
	@PostMapping("/imglist")
	List<ItripImage> getImgListByQuery(@RequestBody ItripImage query)throws Exception;
}
