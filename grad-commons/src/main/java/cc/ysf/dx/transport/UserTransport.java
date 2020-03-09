package cc.ysf.dx.transport;


import cc.ysf.dx.pojo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * >>> 爱旅行 用户信息传输层接口
 */
@FeignClient(name = "grad-biz-provider")
@RequestMapping("/user/trans")
public interface UserTransport {

	/**
	 * >>>  根据查询条件去数据库查询结果
	 * @param userquery
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/list")
	List<User> getUserListByUserQuery(@RequestBody User userquery) throws Exception;

	/**
	 *  >>> 保存注册用户信息
	 * @param user
	 * @return
	 */
	@PostMapping("/save")
	boolean saveUser(@RequestBody User user) throws Exception;

	/**
	 * >>> 通过用户注册账号去redis查询用户激活码
	 * @param userCode
	 * @return
	 */
	@PostMapping(value = "/activeCode")
	String getActiveCodeByUser(@RequestParam String userCode)throws Exception;

	/**
	 * >>> 更新用户数据
	 * @param updateUser
	 * @return
	 */
	@PostMapping("/updateUser")
	boolean updateUser(@RequestBody User updateUser) throws Exception;
}
