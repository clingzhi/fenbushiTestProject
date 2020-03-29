package cc.ysf.dx.transport;

import cc.ysf.dx.pojo.entity.UserLinkUser;
import cc.ysf.dx.pojo.vo.ItripAddUserLinkUserVO;
import cc.ysf.dx.service.UserLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * >>> 爱旅行--系统典信息传输层接口实现类--关联用户
 */
@RestController("userLinkUserTransport")
@RequestMapping("/linkuser/trans")
public class UserLinkUserTransportImpl implements UserLinkUserTransport {
	@Autowired
	private UserLinkUserService userLinkUserService;

	/**
	 * <b>根据当前登陆用户，获得联系人</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryuserlinkuser")
	public List<UserLinkUser> queryUserLinkUserListByQuery(@RequestBody UserLinkUser query)throws Exception {
		return userLinkUserService.getUserLinkUserList(query);
	}

	/**
	 * >>> 添加新客户
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/svaeLinkUser")
	public Boolean saveLinkUser(@RequestBody UserLinkUser userLinkUser) throws Exception {
		return userLinkUserService.saveLinkUser(userLinkUser);
	}

	/**
	 * >>> 删除常用联系人
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/del")
	public Boolean delLinkUser(@RequestParam String ids) throws Exception {
		return userLinkUserService.delLinkUser(ids);
	}

}
