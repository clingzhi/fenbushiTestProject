package cc.ysf.dx.dao;


import cc.ysf.dx.pojo.entity.UserLinkUser;
import cc.ysf.dx.pojo.vo.ItripAddUserLinkUserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * >>> 爱旅行 业务模块下单常用用户数据持久层
 */
@Repository
public interface UserLinkUserDao {
	/**
	 * >>> 查个东西 根据当前登陆用户，获得联系
	 * @param query
	 * @return
	 */
	List<UserLinkUser> findUserLinkUserList(UserLinkUser query) throws Exception;
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
	Boolean delLinkUser(UserLinkUser userLinkUser)throws Exception;
}
