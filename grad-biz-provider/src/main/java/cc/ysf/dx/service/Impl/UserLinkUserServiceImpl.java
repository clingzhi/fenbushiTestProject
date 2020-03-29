package cc.ysf.dx.service.Impl;

import cc.ysf.dx.dao.UserLinkUserDao;
import cc.ysf.dx.pojo.entity.UserLinkUser;
import cc.ysf.dx.pojo.vo.ItripAddUserLinkUserVO;
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

	/**
	 * >>> 添加新客户
	 * @return
	 * @throws Exception
	 */
	public Boolean saveLinkUser(UserLinkUser userLinkUser) throws Exception {
		Boolean flog = userLinkUserDao.saveLinkUser(userLinkUser);
		if (flog){
			return true;
		}
		return false;
	}

	/**
	 * >>> 删除常用联系人
	 * @return
	 * @throws Exception
	 */
	public Boolean delLinkUser(String ids) throws Exception {

		//创建要删除的对象，传入当前登录账号Id，和要删除的常用用户ID
		UserLinkUser userLinkUser = new UserLinkUser();
		//userLinkUser.setUserId(queryUserId.get(0).getId());
		userLinkUser.setId(Long.valueOf(ids));
		Boolean flog = userLinkUserDao.delLinkUser(userLinkUser);
		if (flog){
			return true;
		}
		return false;
	}
}
