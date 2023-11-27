package org.cat.midd.gateway3.springcloud.study;

public class Actuator {
	
	public void 监控状态端点() {
//		GatewayFilter.监控GatewayMetricsFilter();
	}
	
	public void actuator及所有端点的列表() {
		// /gateway actuator端点允许您监视 Spring Cloud Gateway 应用程序并与之交互
		//要远程访问，必须在应用程序属性中启用并通过 HTTP 或 JMX 公开端点
		//以下清单显示了如何执行此操作：
		/**
		 * management.endpoint.gateway.enabled=true # default value
		 * management.endpoints.web.exposure.include=gateway
		 */
		
		/**
		 * globalfilters：
		 * routefilters：
		 * refresh：
		 * routes：
		 * routes/{id} GET：
		 * routes/{id} POST：
		 * routes/{id} DELETE：
		 */
	}
	
	public void 详细Actuator格式() {
		//Spring Cloud Gateway 中添加了一种新的、更详细的格式。 它为每个路由添加了更多详细信息
		//让您可以查看与每个路由关联的谓词和过滤器以及任何可用配置
		//以下示例配置 /actuator/gateway/routes：
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#verbose-actuator-format
		
		//默认情况下启用此功能。 要禁用它，请设置以下属性：
		//spring.cloud.gateway.actuator.verbose.enabled=false
	}
	
	public void 检索RouteFilters() {
		//包括：Global Filters、gateway-route-filters
		
		//Global Filters
		//要检索应用于所有路由的全局过滤器，请向 /actuator/gateway/globalfilters 发出 GET 请求
		//生成的响应类似于以下内容：
		/**
		 * {
		 * "org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter@77856cc5": 10100,
		 * "org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter@4f6fd101": 10000,
		 * "org.springframework.cloud.gateway.filter.NettyWriteResponseFilter@32d22650": -1,
		 * "org.springframework.cloud.gateway.filter.ForwardRoutingFilter@106459d9": 2147483647,
		 * "org.springframework.cloud.gateway.filter.NettyRoutingFilter@1fbd5e0": 2147483647,
		 * "org.springframework.cloud.gateway.filter.ForwardPathFilter@33a71d23": 0,
		 * "org.springframework.cloud.gateway.filter.AdaptCachedBodyGlobalFilter@135064ea": 2147483637,
		 * "org.springframework.cloud.gateway.filter.WebsocketRoutingFilter@23c05889": 2147483646
		 * }
		 */
		//响应包含已就位的全局过滤器的详细信息
		//对于每个全局过滤器，都有过滤器对象的字符串表示
		//例如，org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter@77856cc5
		//和过滤器链中的相应顺序
		
		//gateway-route-filters
		//要检索应用于路由的 GatewayFilter factories ，请向 /actuator/gateway/routefilters 发出 GET 请求
		//生成的响应类似于以下内容：
		/**
		 * {
		 * "[AddRequestHeaderGatewayFilterFactory@570ed9c configClass = AbstractNameValueGatewayFilterFactory.NameValueConfig]": null,
		 * "[SecureHeadersGatewayFilterFactory@fceab5d configClass = Object]": null,
		 * "[SaveSessionGatewayFilterFactory@4449b273 configClass = Object]": null
		 * }
		 */
	}
	
	public void 刷新路由缓存() {
		//要清除路由缓存，请向 /actuator/gateway/refresh 发出 POST 请求
		//该请求返回没有响应正文的 200
	}
	
	public void 检索网关中定义的路由() {
		//要检索网关中定义的路由，请向 /actuator/gateway/routes 发出 GET 请求
		//生成的响应类似于以下内容：
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#retrieving-the-routes-defined-in-the-gateway
		//包括：route_id、route_object.predicate、route_object.filters、order
	}
	
	public void 检索每个ParticularRoute的信息() {
		//要检索有关单个路由的信息，请向 /actuator/gateway/routes/{id} 发出 GET 请求
		//例如，/actuator/gateway/routes/first_route
		//生成的响应类似于以下内容：
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#gateway-retrieving-information-about-a-particular-route
		//包括：id、predicates、filters、uri、order
	}
	
	public void 创建or删除ParticularRoute() {
		//要创建路由，请使用指定路由字段的 JSON 正文向 /gateway/routes/{id_route_to_create} 发出 POST 请求
		//要删除路由，请向 /gateway/routes/{id_route_to_delete} 发出 DELETE 请求
	}
}
