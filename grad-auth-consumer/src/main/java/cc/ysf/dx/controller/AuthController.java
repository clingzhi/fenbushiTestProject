package cc.ysf.dx.controller;

import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.enums.UserTypeEnum;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.pojo.vo.UserVO;
import cc.ysf.dx.transport.UserTransport;
import cc.ysf.dx.util.MD5Util;
import cc.ysf.dx.util.RegValidationUtil;
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
	 * >>> 注册时对用户名进行检测--邮箱注册
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/ckusr")
	public ResponseDto<Object> checkUserEmailForRegistry(String name) throws Exception{
		//检验用户提交的name属性是否合法(邮箱格式，还没写)
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

	@PostMapping("/doregister")
	public ResponseDto<Object> registryUser(@RequestBody UserVO userVO) throws Exception{
		// 检验用户注册的邮箱是否有效
		if (RegValidationUtil.validateEmail(userVO.getUserCode())
				&& userVO.getUserPassword()!= null && !"".equals(userVO.getUserPassword())){
			// 对密码进行MD5加密
			userVO.setUserPassword(MD5Util.encrypt(userVO.getUserPassword()));
			//  将 UserVo对象转化成 User 对象
			User user = new User();
			//  org.springframework 所携带的方法，能够快速进行对象类型之间的转换
			//  本质为扫描源source对象类，根据对应类型转化为目标target 类型
			BeanUtils.copyProperties(userVO,user);
			// 当调用该方法时，用户属于自助注册
			user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());

			// 调用传输层，使传输层接口，远程向 生产者的传输层实现类（控制层）发起请求
			boolean flag = userTransport.saveUser(user);
			if(flag){
				//保存成功
				return ResponseDto.success();
			}
		}
		return ResponseDto.failure("注册失败!");
	}
}
