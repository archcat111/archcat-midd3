package org.cat.midd.gateway3.springcloud.study;

public class Log {
	
	public void ReactorNetty访问日志() {
		//要启用 Reactor Netty 访问日志，请设置 -Dreactor.netty.http.server.accessLogEnabled=true
		//它必须是 Java 系统属性，而不是 Spring Boot 属性
		
		//可以将日志记录系统配置为具有单独的访问日志文件。 以下示例创建一个 Logback 配置：
		/**
		 * <appender name="async" class="ch.qos.logback.classic.AsyncAppender">
		 * 		<appender-ref ref="accessLog" />
		 * </appender>
		 * <logger name="reactor.netty.http.server.AccessLog" level="INFO" additivity="false">
		 * 		<appender-ref ref="async"/>
		 * </logger>
		 */
	}
	
	public void 监听() {
		//Reactor Netty HttpClient 和 HttpServer 可以启用监听
		//当与将 reactor.netty 日志级别设置为 DEBUG 或 TRACE 结合使用时，它可以记录信息
		//例如通过网络发送和接收的标头和正文
		//要启用监听，请分别为 HttpServer 和 HttpClient 设置：
		//spring.cloud.gateway.httpserver.wiretap=true 或 spring.cloud.gateway.httpclient.wiretap=true 
	}
}
