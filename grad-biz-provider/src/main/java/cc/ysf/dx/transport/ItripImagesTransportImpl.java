package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.ItripImage;
import cc.ysf.dx.service.ItripImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * >>> 查找图片传输层接口实现类
 */
@RestController("itripImagesTransport")
@RequestMapping("/img/trans")
public class ItripImagesTransportImpl implements ItripImagesTransport {
	@Autowired
	private ItripImageService itripImgService;
	/**
	 * >>> 根据查询条件查询
	 * @param query
	 * @return
	 */
	@PostMapping("/imglist")
	public List<ItripImage> getImgListByQuery(@RequestBody ItripImage query) throws Exception{
		return itripImgService.getImgListByQuery(query);
	}
}
