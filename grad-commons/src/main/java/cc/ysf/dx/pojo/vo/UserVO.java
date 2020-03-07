package cc.ysf.dx.pojo.vo;

import java.io.Serializable;

/**
 * >>> 用户视图层对象---接收客户端表单中的用户注册信息VO
 */
public class UserVO implements Serializable {
	private static final long serialVersionUID = 1924081071717430658L;
	private String userCode;
	private String userPassword;
	private String userName;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
