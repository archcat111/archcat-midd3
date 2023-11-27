package org.cat.midd.gateway3.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 * @author 王云龙
 * @date 2022年5月6日 上午10:46:32
 * @version 1.0
 * @description 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		application.setWebApplicationType(WebApplicationType.REACTIVE);
		application.setApplicationStartup(new BufferingApplicationStartup(1000));
		application.run(args);
	}
	
}
