package cc.ysf.dx.transport;

import cc.ysf.dx.dao.UserDao;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * >>> 爱旅行 传输层实现类
 */
@RestController("userTransport")
@RequestMapping("/user/trans")
public class UserTransportImpl implements UserTransport {
	@Autowired
	private UserService userService;

	/**
	 *  >>> 根据查询条件去数据库查询结果
	 * @param userquery
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/list")
	public List<User> getUserListByUserQuery(@RequestBody User userquery) throws Exception {
		return userService.getUserListByUserQuery(userquery);
	}

	/**
	 *  >>> 根据传输队形储存用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/save")
	public boolean saveUser(@RequestBody User user) throws Exception {
		return userService.saveUser(user);
	}

	/**
	 * >>> 通过用户注册账号去redis查询用户激活码
	 * @param userCode
	 * @return
	 */
	@PostMapping("/activeCode")
	public String getActiveCodeByUser(@RequestParam String userCode) throws Exception {
		return userService.getActiveCodeByUser(userCode);
	}

	/**
	 * >>> 更新用户数据
	 * @param updateUser
	 * @return
	 */
	@PostMapping("/updateUser")
	public boolean updateUser(@RequestBody User updateUser) throws Exception {
		return userService.updateUser(updateUser);
	}
}
