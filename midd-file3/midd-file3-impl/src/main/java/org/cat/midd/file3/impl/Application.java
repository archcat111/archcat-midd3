package org.cat.midd.file3.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @author 王云龙
 * @date 2017年12月5日 上午11:47:34
 * @version 1.0
 * @description 文件微服务启动类
 *
 */

@SpringBootApplication 
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"org.cat.app.user3.client.feign"})
public class Application {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.setApplicationStartup(new BufferingApplicationStartup(1000));
		application.run(args);
	}
}
