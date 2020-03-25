package cc.ysf.dx.controller;


import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.UserLinkUser;
import cc.ysf.dx.transport.UserLinkUserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
