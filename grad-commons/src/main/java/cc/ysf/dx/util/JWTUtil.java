package cc.ysf.dx.util;

import cc.ysf.dx.util.constant.SecretKeyConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * >>> JWT工具类  用于token的生成 和 校验
 * >> 当代码进行到用户登陆成功时，代码中生成的token保存于客户端浏览器，token中可以包含用户的ID，账号，密码等信息
 * >> 当浏览器携带token的请求再次到达服务器时，服务器会使用与token约定好的秘钥对token进行校验
 * >> 如果秘钥正确，则开始使用密文内容，密文中字段的加密/解密算法开始工作，完成后就可以锁定对应的用户。
 * > 这样，就实现了用户在不同的服务器上都能实现业务的功能
 */
public class JWTUtil {
	// 使用加密算法配置秘钥
	private static Algorithm algorithm = Algorithm.HMAC256(SecretKeyConstant.SECRET_key);

	/**
	 * >>> 生成令牌
	 * @return
	 * @throws Exception
	 */
	public static String createToken(Long id)throws Exception{
		// 创建JWTCreator.Builder对象，用于创建Token
		JWTCreator.Builder builder = JWT.create();

		// 创建一个Map集合，用于存储JWT的头部信息
		Map<String, Object> header = new HashMap<String, Object>();
		// 设定加密算法
		header.put("alg", "HS256");
		// 设定token类型
		header.put("typ", "JWT");

		//将头部信息插入语JWT
		builder.withHeader(header);
		// 设置有效载荷
		builder.withClaim("id", id);
		// 为前端设置过期时间--当前时间+有效时间=到期时间
		Long expTime = new Date().getTime() + (30 * 60 * 1000);
		builder.withClaim("expTime", new Date(expTime));

		// 加入签名部分，生成最终的Token字符串
		return builder.sign(algorithm);
	}

	/**
	 * >>> 解析令牌
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Long validateToken(String token) throws Exception {
		if (token != null && !"".equals(token.trim())) {
			try {
				//require ：需要
				//verifier ：验证器
				//decoded ：解码
				// 配置验证器需要的秘钥--使用秘钥（algorithm)核对约定的秘钥信息
				JWTVerifier verifier = JWT.require(algorithm).build();
				// 进行解密校验--使用JWT的加密算法对密文解密
				//使用携带秘钥的解码器对token进行验证，解密
				DecodedJWT decodedJWT = verifier.verify(token);
				// 当没有产生异常信息时，说明该token已经被成功识别，那么获取相关数据
				Claim id = decodedJWT.getClaim("id");
				return id.asLong();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1L;
	}

	/**
	 * >> test
	 */
	public static void main(String[] args)throws Exception {
		//System.out.println(createToken(22L));
		System.out.println(validateToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHBUaW1lIjoxNTgzODU1OTA5LCJpZCI6MjJ9.zKc-SAoiBLdYhyA5cXwRwnItqg4H_hUMDFih4zc2IHM"
		));
	}

}
