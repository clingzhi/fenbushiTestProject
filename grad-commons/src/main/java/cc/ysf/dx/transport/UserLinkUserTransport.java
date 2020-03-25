package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.UserLinkUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
