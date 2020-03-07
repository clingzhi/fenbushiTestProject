package cc.ysf.dx.dao;

import cc.ysf.dx.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * >>> 爱旅行 业务模块数据持久层
 */
@Repository
public interface UserDao {
	/**
	 *   >>> 根据条件查询用户注册账号是否存在
	 * @param userquery
	 * @return
	 * @throws Exception
	 */
	List<User> findUserListByQuery(User userquery) throws Exception;

	/**
	 *  >>> 根据传输对象保存用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int saveUser(User user) throws Exception;
}
