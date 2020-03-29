package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.UserLinkUser;
import cc.ysf.dx.pojo.vo.ItripAddUserLinkUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * >>>爱旅行--系统典信息传输层接口 --关联用户列表
 */
@FeignClient("grad-biz-provider")
@RequestMapping("/linkuser/trans")
public interface UserLinkUserTransport {

	/**
	 * <b>根据当前登陆用户，获得联系人</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryuserlinkuser")
	List<UserLinkUser> queryUserLinkUserListByQuery(@RequestBody UserLinkUser query)throws Exception;
	/**
	 * >>> 添加新客户
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/svaeLinkUser")
	Boolean saveLinkUser(@RequestBody UserLinkUser userLinkUser)throws Exception;


	/**
	 * >>> 删除常用联系人
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/del")
	Boolean delLinkUser(@RequestParam String ids)throws Exception;

}
