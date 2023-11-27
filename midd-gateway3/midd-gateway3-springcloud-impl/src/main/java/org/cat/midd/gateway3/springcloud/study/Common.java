package org.cat.midd.gateway3.springcloud.study;

public class Common {
	
	public void ProxyExchange() {
		//Spring Cloud Gateway 提供了一个名为 ProxyExchange 的实用程序对象
		//可以在常规 Spring Web 处理程序中将其用作方法参数
		//它通过镜像 HTTP 动词的方法支持基本的下游 HTTP 交换
		//使用 MVC，它还支持通过 forward() 方法转发到本地处理程序
		//要使用 ProxyExchange，请在类路径中包含（spring-cloud-gateway-mvc 或 spring-cloud-gateway-webflux）
		/**
		 * @RestController
		 * @SpringBootApplication
		 * public class GatewaySampleApplication {
		 * 		@Value("${remote.home}")
		 * 		private URI home;
		 * 
		 * 		@GetMapping("/test")
		 * 		public ResponseEntity<?> proxy(ProxyExchange<byte[]> proxy) throws Exception {
		 * 			return proxy.uri(home.toString() + "/image/png").get();
		 * 		}
		 * }
		 */
		/**
		 * @RestController
		 * @SpringBootApplication
		 * public class GatewaySampleApplication {
		 * 		@Value("${remote.home}")
		 * 		private URI home;
		 * 
		 * 		@GetMapping("/test")
		 * 		public Mono<ResponseEntity<?>> proxy(ProxyExchange<byte[]> proxy) throws Exception {
		 * 			return proxy.uri(home.toString() + "/image/png").get();
		 * 		}
		 * }
		 */
		/**
		 * @GetMapping("/proxy/path/**")
		 * public ResponseEntity<?> proxyPath(ProxyExchange<byte[]> proxy) throws Exception {
		 * 		String path = proxy.path("/proxy/path/");
		 * 		return proxy.uri(home.toString() + "/foos/" + path).get();
		 * }
		 */
	}
}
