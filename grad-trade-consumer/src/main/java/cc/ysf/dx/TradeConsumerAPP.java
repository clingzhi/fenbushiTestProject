package cc.ysf.dx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * >>> 爱旅行 支付模块启动器
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class TradeConsumerAPP {
	public static void main(String[] args) {
		SpringApplication.run(TradeConsumerAPP.class, args);
	}
}
