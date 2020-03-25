package cc.ysf.dx.service.Impl;

import cc.ysf.dx.dao.UserLinkUserDao;
import cc.ysf.dx.pojo.entity.UserLinkUser;
import cc.ysf.dx.service.UserLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * >>> 爱旅行--系统典信息业务层接口--关联用户
 */
@Service("userLinkUserService")
@Transactional
public class UserLinkUserServiceImpl implements UserLinkUserService {
	@Autowired
	private UserLinkUserDao userLinkUserDao;
	/**
	 * >>> 查个东西 根据当前登陆用户，获得联系
	 * @param query
	 * @return
	 */
	public List<UserLinkUser> getUserLinkUserList(UserLinkUser query) throws Exception {
		List<UserLinkUser> userLinkUserList= userLinkUserDao.findUserLinkUserList(query);

		if (userLinkUserList != null){
			return userLinkUserList;
		}

		return new ArrayList<UserLinkUser>();
	}
}
