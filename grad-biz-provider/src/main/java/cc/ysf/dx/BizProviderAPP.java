package cc.ysf.dx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * >>> 主业务模块生产者启动类
 */
@MapperScan("cc.ysf.dx.dao")
@EnableEurekaClient
@SpringBootApplication
public class BizProviderAPP {
	public static void main(String[] args) {
		SpringApplication.run(BizProviderAPP.class, args);
	}
}
