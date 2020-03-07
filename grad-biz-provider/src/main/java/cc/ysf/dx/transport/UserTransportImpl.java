package cc.ysf.dx.transport;

import cc.ysf.dx.dao.UserDao;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
