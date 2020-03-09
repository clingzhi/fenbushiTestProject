package cc.ysf.dx.controller;

import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.enums.UserActivatedEnum;
import cc.ysf.dx.base.enums.UserTypeEnum;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.pojo.vo.UserVO;
import cc.ysf.dx.transport.UserTransport;
import cc.ysf.dx.util.MD5Util;
import cc.ysf.dx.util.RegValidationUtil;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * >>> 爱旅行 认证控制模块
 *
 */

@RestController("authController")
@RequestMapping("/auth/api")
public class AuthController extends BaseController {
	@Autowired
	private UserTransport userTransport;

	/**
	 * >>> 注册时对用户名进行检测是否存在--邮箱注册
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/ckusr")
	public ResponseDto<Object> checkUserEmailForRegistry(String name) throws Exception{
		//检验用户提交的name属性是否合法
		if(RegValidationUtil.validateEmail(name)){
			//去数据库检查邮箱账号是否已经存在
			User userquery = new User();
			userquery.setUserCode(name);
			// 进行查询
			List<User> userList = userTransport.getUserListByUserQuery(userquery);
			// 查询结果判断
			if (userList == null || userList.size() == 0){
				//说明此时数据库不存在该邮箱，可以注册
				return ResponseDto.success();
			}
		}
		return ResponseDto.failure("该邮箱已经被注册！");
	}

	/**
	 *  >>> 注册用户账号--邮箱
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/doregister")
	public ResponseDto<Object> registryUser(@RequestBody UserVO userVO) throws Exception{
		// 检验用户注册的邮箱是否有效
		if (RegValidationUtil.validateEmail(userVO.getUserCode())
				&& userVO.getUserPassword()!= null && !"".equals(userVO.getUserPassword())){

			//去数据库检查邮箱账号是否已经存在
			User userquery = new User();
			userquery.setUserCode(userVO.getUserCode());
			// 进行查询
			List<User> userList = userTransport.getUserListByUserQuery(userquery);
			// 查询结果判断
			if (userList == null || userList.size() == 0){

				// 对密码进行MD5加密
				userVO.setUserPassword(MD5Util.encrypt(userVO.getUserPassword()));
				//  将 UserVo对象转化成 User 对象
				User user = new User();
				//  org.springframework 所携带的方法，能够快速进行对象类型之间的转换
				//  本质为扫描源source对象类，根据对应类型转化为目标target 类型
				BeanUtils.copyProperties(userVO,user);
				// 当调用该方法时，用户属于自助注册
				user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
				// 将激活状态设置为未激活
				user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());

				// 调用传输层，使传输层接口，远程向 生产者的传输层实现类（控制层）发起请求
				boolean flag = userTransport.saveUser(user);
				if(flag){
					//保存成功
					return ResponseDto.success();
				}
			}
			return ResponseDto.failure("该邮箱已被注册!");
		}
		return ResponseDto.failure("注册失败!");
	}

	/**
	 *  >>> 激活账号--邮箱
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/activate")
	public ResponseDto<Object> activeUser(String user,String code) throws Exception{
		//判断账号(user) 和 激活码 是否为空
		if(user != null && !"".equals(user.trim()) && code != null && !"".equals(code)){
			//到redis中查找对应的code
			String activeCode = userTransport.getActiveCodeByUser(user);
			// 判断激活码是否正确
			if(code.equals(activeCode)){
				//修改用户状态
				User updateUser = new User();
				updateUser.setUserCode(user);
				updateUser.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
				//在数据库中更新用户状态
				userTransport.updateUser(updateUser);
				return ResponseDto.success();
			}
			return ResponseDto.failure("激活码不正确！");
		}
		return ResponseDto.failure("激活失败！");
	}

	/**
	 * >>> 用户注册--手机号
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/registerbyphone")
	public ResponseDto<Object> registerByCellphone(@RequestBody UserVO userVO)throws Exception{
		// 检验用户注册的邮箱是否有效
		if (RegValidationUtil.validateCellphone(userVO.getUserCode())
				&& userVO.getUserPassword()!= null && !"".equals(userVO.getUserPassword())){

			//去数据库检查邮箱账号是否已经存在
			User userquery = new User();
			userquery.setUserCode(userVO.getUserCode());
			// 进行查询
			List<User> userList = userTransport.getUserListByUserQuery(userquery);
			// 查询结果判断
			if (userList == null || userList.size() == 0){

				// 对密码进行MD5加密
				userVO.setUserPassword(MD5Util.encrypt(userVO.getUserPassword()));
				//  将 UserVo对象转化成 User 对象
				User user = new User();
				//  org.springframework 所携带的方法，能够快速进行对象类型之间的转换
				//  本质为扫描源source对象类，根据对应类型转化为目标target 类型
				BeanUtils.copyProperties(userVO,user);
				// 当调用该方法时，用户属于自助注册
				user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
				// 将激活状态设置为未激活
				user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());

				// 调用传输层，远程调用生产者进行 手机号码注册
				boolean flag = userTransport.saveUser(user);
				if(flag){
					//保存成功
					return ResponseDto.success();
				}
			}
			return ResponseDto.failure("该手机号码已被注册!");
		}
		return ResponseDto.failure("注册失败！");
	}

	/**
	 * >>> 用户激活--手机号
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/validatephone")
	public ResponseDto<Object> activeUserByCellphone(String user, String code)throws Exception{
		//判断账号(user) 和 激活码 是否为空
		if(user != null && !"".equals(user.trim()) && code != null && !"".equals(code)){
			//到redis中查找对应的code
			String activeCode = userTransport.getActiveCodeByUser(user);
			// 判断激活码是否正确
			if(code.equals(activeCode)){
				//修改用户状态
				User updateUser = new User();
				updateUser.setUserCode(user);
				updateUser.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
				//在数据库中更新用户状态
				userTransport.updateUser(updateUser);
				return ResponseDto.success();
			}
			return ResponseDto.failure("激活码不正确！");
		}
		return ResponseDto.failure("激活失败！");
	}
}
