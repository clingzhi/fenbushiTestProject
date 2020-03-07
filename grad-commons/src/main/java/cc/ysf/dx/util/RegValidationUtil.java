package cc.ysf.dx.util;

/**
 * <b>爱旅行-使用正则验证工具类</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
public class RegValidationUtil {
	// 设置电子邮件正则表达式
	private static final String emailRegEx = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

	/**
	 * <b>判断电子邮件信息是否格式正确</b>
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		// 判断此时的email地址不能为null，并且不能是空字符串
		if (email != null && !"".equals(email)) {
			return email.matches(emailRegEx);
		}
		return false;
	}

	public static void main(String[] args) {
		String email = "chengchi@163.com";
		System.out.println(validateEmail(email));
	}
}
