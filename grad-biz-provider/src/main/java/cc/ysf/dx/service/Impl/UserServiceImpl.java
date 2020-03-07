package cc.ysf.dx.service.Impl;

import cc.ysf.dx.dao.UserDao;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.service.UserService;
import cc.ysf.dx.util.ActiveCodeUtil;
import cc.ysf.dx.util.MailSenderUtil;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * >>> 爱旅行 主业务模块 业务层实现类
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private MailSenderUtil mailSenderUtil;
	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * >>> 根据查询条件去数据库查询结果
	 * @param userquery
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserListByUserQuery(User userquery) throws Exception {
		return userDao.findUserListByQuery(userquery);
	}

	/**
	 *  >>> 根据传输对象保存注册用户信息
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user) throws Exception {
		// 设定用户的注册时间
		user.setCreationDate(new Date());
		// 使用数据持久层进行数据保存
		int flag = userDao.saveUser(user);
		if (flag >0){
			// 用户保存成功,生成激活码
			String activeCode = ActiveCodeUtil.createActiveCode();
			// 使用StringRedisTemplate将验证码进行保存，key为用户的email地址，value就是激活码
			redisTemplate.opsForSet().add(user.getUserCode(), activeCode);
			// 设置存储于redis中的数据存活时间
			redisTemplate.expire(user.getUserCode(), 30, TimeUnit.MINUTES);
			// 通过发送邮件，将激活码发送给用户
			return mailSenderUtil.sendActiveCodeMail(user.getUserCode(), activeCode);
		}
		return false;
	}
}
