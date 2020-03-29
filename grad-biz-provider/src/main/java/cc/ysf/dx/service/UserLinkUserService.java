package cc.ysf.dx.service;

import cc.ysf.dx.pojo.entity.UserLinkUser;
import cc.ysf.dx.pojo.vo.ItripAddUserLinkUserVO;

import java.util.List;

/**
 * >>> 爱旅行--系统典信息业务层接口--关联用户
 */
public interface UserLinkUserService {
	/**
	 * >>> 查个东西 根据当前登陆用户，获得联系
	 * @param query
	 * @return
	 */
	List<UserLinkUser> getUserLinkUserList(UserLinkUser query) throws Exception;
	/**
	 * >>> 添加新客户
	 * @return
	 * @throws Exception
	 */
	Boolean saveLinkUser(UserLinkUser userLinkUser)throws Exception;
	/**
	 * >>> 删除常用联系人
	 * @return
	 * @throws Exception
	 */
	Boolean delLinkUser(String ids)throws Exception;
}
