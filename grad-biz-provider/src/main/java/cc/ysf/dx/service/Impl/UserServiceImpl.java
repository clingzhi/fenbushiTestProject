package cc.ysf.dx.service.Impl;

import cc.ysf.dx.dao.UserDao;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.service.UserService;
import cc.ysf.dx.util.ActiveCodeUtil;
import cc.ysf.dx.util.MailSenderUtil;
import cc.ysf.dx.util.RegValidationUtil;
import cc.ysf.dx.util.SmsSenderUtil;
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
	@Autowired
	private SmsSenderUtil smsSenderUtil;

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
			redisTemplate.opsForValue().set(user.getUserCode(), activeCode);
			// 设置存储于redis中的数据存活时间
			redisTemplate.expire(user.getUserCode(), 30, TimeUnit.MINUTES);

			//判断此时用户是用邮箱注册还是手机注册
			if(RegValidationUtil.validateEmail(user.getUserCode())){
				// 通过发送邮件，将激活码发送给用户
				return mailSenderUtil.sendActiveCodeMail(user.getUserCode(), activeCode);
				} else if(RegValidationUtil.validateCellphone(user.getUserCode())){
					// 使用手机号码注册，将激活码发送到对方的手机中
					return smsSenderUtil.sendSms(user.getUserCode(), activeCode);
				}
			}
		return false;
	}

	/**
	 * >>> 通过用户注册账号去redis查询用户激活码
	 * @param userCode
	 * @return
	 */
	public String getActiveCodeByUser(String userCode) throws Exception {
		// 通过redis查询激活码
		String activeCode = redisTemplate.opsForValue().get(userCode);
		return activeCode;
	}

	/**
	 * >>> 更新用户数据
	 * @param updateUser
	 * @return
	 */
	public boolean updateUser(User updateUser) throws Exception {
		int flag = userDao.updateUser(updateUser);
			if (flag > 0){
				return true;
			}
		return false;
	}
}
