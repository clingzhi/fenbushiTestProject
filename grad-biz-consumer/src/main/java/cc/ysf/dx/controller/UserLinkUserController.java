package cc.ysf.dx.controller;


import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.pojo.entity.UserLinkUser;
import cc.ysf.dx.pojo.vo.ItripAddUserLinkUserVO;
import cc.ysf.dx.transport.UserLinkUserTransport;
import cc.ysf.dx.transport.UserTransport;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;

/**
 * >>> 爱旅行 -常用用户连接列表控制层
 */
@RestController("userLinkUserController")
@RequestMapping("/biz/api/userinfo")
public class UserLinkUserController extends BaseController {
	@Autowired
	private UserLinkUserTransport userLinkUserTransport;
	@Autowired
	private UserTransport userTransport;

	/**
	 * <b>根据当前登陆用户，获得联系人</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryuserlinkuser")
	public ResponseDto<Object> queryUserLinkUser() throws Exception {
		// 通过 Cookie 获得当前登陆对象
		String userCode = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			//如果Cookie中token的key为user,取其值作为userCode
			if ("user".equals(cookie.getName())) {
				userCode = cookie.getValue();
			}
		}

		// 封装查询对象
		UserLinkUser query = new UserLinkUser();
		query.setUserCode(userCode);

		List<UserLinkUser> userLinkUserList = userLinkUserTransport.queryUserLinkUserListByQuery(query);
		return ResponseDto.success(userLinkUserList);
	}

	/**
	 * >>> 添加新客户
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/adduserlinkuser")
	public ResponseDto<Object> addUserLinkUser(@RequestBody UserLinkUser userLinkUser)throws Exception{
	// 获得当前登录账号
		String userCode = "" ;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie:cookies) {
			if ("user".equals(cookie.getName())){
				userCode=cookie.getValue();
			}
		}
		User user = new User();
		user.setUserCode(userCode);
		List<User> queryUserId = userTransport.getUserListByUserQuery(user);
		//传入登录用户Id
		userLinkUser.setUserId(queryUserId.get(0).getId());

		//添加常用用户
		Boolean flog = userLinkUserTransport.saveLinkUser(userLinkUser);

		return ResponseDto.success(flog);
	}

	/**
	 * >>> 删除常用联系人
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/deluserlinkuser")
	public ResponseDto<Object> deleteLinkUser(@RequestParam String ids)throws Exception{

		//执行删除
		Boolean flog = userLinkUserTransport.delLinkUser(ids);
		if (flog){
			return ResponseDto.success("删除成功！");
		}
		return ResponseDto.success("删除失败！");

	}
}
