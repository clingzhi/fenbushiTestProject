package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.User;

import java.util.List;

/**
 * >>> 爱旅行 主业务模块用户信息 业务层接口
 *
 */

public interface UserService {
	/**
	 * >>> 根据查询条件去数据库查询结果
	 * @param userquery
	 * @return
	 * @throws Exception
	 */
	List<User> getUserListByUserQuery(User userquery) throws Exception;

	/**
	 *  >>> 根据传输对象保存注册用户信息
	 * @param user
	 * @return
	 */
	boolean saveUser(User user) throws Exception;
}
