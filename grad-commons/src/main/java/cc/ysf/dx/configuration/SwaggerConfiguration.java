package cc.ysf.dx.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * >>> swagger配置类
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	// 创建生产BEAN 的方法, 需要Docket 对象
	@Bean
	public Docket createDocaket(){
		//创建Docket
		Docket dockate = new Docket(DocumentationType.SWAGGER_2);
		dockate.apiInfo(apiInfo());
		return dockate;
	}

	/**
	 * >>> 创建UI
	 * @return
	 */
	// 创建ApiInfoBuilder
	private ApiInfo apiInfo(){
		ApiInfo apiInfo =new ApiInfoBuilder()
				//生成文档接口
				.title("爱旅行--项目文档接口")
				.version("2.1.2")
				.contact(new Contact("备注1","","" ))


				.build();
			return apiInfo;
	}
}
