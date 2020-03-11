package cc.ysf.dx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * >>> 主业务模块消费者启动类
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class BizConsumerAPP {
	public static void main(String[] args) {
		SpringApplication.run(BizConsumerAPP.class, args);
	}
}
