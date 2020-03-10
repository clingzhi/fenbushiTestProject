package cc.ysf.dx.util.constant;

import java.util.Properties;

/**
 * >>> 系统工具常量类 -- 打开token的秘钥 读取与使用
 */
public class SecretKeyConstant {
	private static Properties props = new Properties();

	static {
		try {
			props.load(SecretKeyConstant.class.getClassLoader().getResourceAsStream("prop/secretKeyCommons.properties"));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static final String SECRET_key = props.getProperty("secret.key");
}
