package cc.ysf.dx.util;

import java.util.Random;

/**
 * >>> 激活码 生成工具类
 */
public class ActiveCodeUtil {

	/**
	 * >>> 随机生成6位随机数激活码
	 */
	public static String createActiveCode(){
		StringBuffer stringBuffer = new StringBuffer();
		Random random= new Random();
			for (int i=0;i<6;i++){
				stringBuffer.append(random.nextInt(10));

			}
			return stringBuffer.toString();
	}
}
