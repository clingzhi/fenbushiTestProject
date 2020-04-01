package cc.ysf.dx.controller;

import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.enums.ItripImgType;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.ItripImage;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController("uploadController")
@RequestMapping("/itrip/biz/api")
public class UploadController extends BaseController {

	@PostMapping("/comment/upload")
	public ResponseDto<Object> upload(@RequestPart("file") MultipartFile file) throws Exception{
		//创建返回前端访问时的图片url的List集合
		List<String> imgUrlList = new ArrayList<>();
		//声明保存地址和访问地址两个变量
		String imgSavePath = null;
		String imgVisitUrl = null;
		//获取传入对象数组的长度
		int fileCount = request.getParts().size();
		if (fileCount>0 && fileCount<=4){
			// 传入数量符合要求，进入循环
			for (int i=0;i<fileCount;i++){
				String initName = file.getOriginalFilename();
				//判断名称是否符合规则
				Boolean flag = initName.toLowerCase().contains(".jpg") ||
						initName.toLowerCase().contains(".gif") ||
						initName.toLowerCase().contains(".png") ;
				if (!initName.equals("") && flag){
					//创建新的文件名
					String suffix = initName.substring(initName.lastIndexOf("."), initName.length());
					String newName = "ITRIP"+System.currentTimeMillis()+suffix;

					//获得输入流
					InputStream is = file.getInputStream();

					//设置文件保存地址
					imgSavePath="E:/IDEA_Project/nginx-1.16.1/view/itrip/static/commentImg";
					//通过保存地址可以得到文件夹，文件对象
					File commentImg = new File(imgSavePath);
						//判断commentImg文件夹是否存在
					if (!commentImg.exists()) {
						// 新建文件夹
						commentImg.mkdirs();
					}

					//设置前端访问的URL
					imgVisitUrl="http://"+request.getServerName()+"/static/commentImg/"+newName;
					//imgVisitUrl="http://img.itrip.project.bdqn.cn/comment/8-6754744453467-4566512.jpg";
					System.out.println("前端图片访问地址:");
					System.out.println(imgVisitUrl);
					//将访问地址添加到返回集合
					imgUrlList.add(imgVisitUrl);

					//设置输出流
					// 根据保存地址和文件名 创建输出对象
					File saveFile = new File(imgSavePath + File.separator + newName);
					OutputStream os = new FileOutputStream(saveFile);

					IOUtils.copy(is, os);
					is.close();
					os.close();

					System.out.println("上传成功");
				}
			}
		}

		return ResponseDto.success(imgUrlList);
	}
}
