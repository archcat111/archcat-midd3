package org.cat.midd.gateway3.springcloud.study;

public class Configuration {
	
	public void CommonApplicationProperties() {
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/appendix.html
		
		/**
		 * 
		 * @author 王云龙
		 * @date 2022年5月6日 上午10:46:32
		 * @version 1.0
		 * @description 
		 * spring-cloud-gateway-server-3.0.4.jar
		 * 		{@linkplain GatewayAutoConfiguration}，spring.cloud.gateway.enabled默认为true，会执行该自动化配置
		 * 			{@linkplain ForwardedHeadersFilter}，spring.cloud.gateway.forwarded.enabled默认为true
		 * 			{@linkplain XForwardedHeadersFilter}，spring.cloud.gateway.x-forwarded.enabled默认为true
		 * 			{@linkplain GatewayControllerEndpoint}，spring.cloud.gateway.actuator.verbose.enabled，默认为true
		 * 		{@linkplain GatewayLoadBalancerProperties}，spring.cloud.gateway.loadbalancer相关
		 * 		{@linkplain GatewayMetricsProperties}，spring.cloud.gateway.metrics相关
		 * 		{@linkplain GatewayProperties}，spring.cloud.gateway.routes、defaultFilters相关
		 * 		{@linkplain GlobalCorsProperties}，spring.cloud.gateway.globalcors相关
		 * 		{@linkplain HttpClientProperties}，spring.cloud.gateway.httpclient相关
		 * 
		 * 		{@linkplain DiscoveryLocatorProperties}，spring.cloud.gateway.discovery.locator相关
		 * 
		 * spring-cloud-commons-3.0.4.jar
		 * 		{@linkplain SimpleReactiveDiscoveryClientAutoConfiguration}
		 * 			{@linkplain SimpleReactiveDiscoveryProperties}，spring.cloud.discovery.client.simple相关，其中local会自动初始化服务自身信息
		 * 
		 * spring-cloud-gateway-webflux-3.0.4.jar
		 * 		{@linkplain ProxyResponseAutoConfiguration}，@ConditionalOnWebApplication
		 * 		{@linkplain ProxyProperties}，spring.cloud.gateway.proxy相关
		 *
		 */
	}
	
	public void 配置格式() {
		//如下配置等效：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Cookie=mycookie,mycookievalue
		 */
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - name: Cookie
		 * 			args:
		 * 				name: mycookie
		 * 				regexp: mycookievalue
		 */
	}
	
	public void RouteDefinitionLocator() {
		//Spring Cloud Gateway 的配置由 RouteDefinitionLocator 实例的集合驱动
		
		//默认情况下，PropertiesRouteDefinitionLocator 使用 Spring Boot 的 @ConfigurationProperties 机制加载属性
		/**
		 * 实现有：
		 * PropertiesRouteDefinitionLocator：从配置文件 (例如，YML / Properties 等) 读取
		 * RouteDefinitionRepository：从存储器 (例如，内存 / Redis / MySQL 等) 读取
		 * DiscoveryClientRouteDefinitionLocator：从注册中心 (例如，Eureka / Consul / Zookeeper / Etcd 等) 读取
		 * CompositeRouteDefinitionLocator：组合多种 RouteDefinitionLocator 的实现，为 RouteDefinitionRouteLocator 提供统一入口
		 * CachingRouteDefinitionLocator：也是 RouteDefinitionLocator 的实现类，已经被 CachingRouteLocator 取代
		 */
	}
	
	public void CachingRouteLocator() {
		//Spring容器加载的时候，会把路由都放到CachingRouteLocator里，后续运行时只会和CachingRouteLocator打交道
	}
	
	public void RouteMetadataConfiguration() {
		//可以使用元数据为每个路由配置附加参数，如下所示：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: route_with_metadata
		 * 		  uri: https://example.org
		 * 		  metadata:
		 * 		  	optionName: "OptionValue"
		 * 			compositeObject:
		 * 				name: "value"
		 * 			iAmNumber: 1
		 */
		//您可以从exchange中获取所有元数据属性，如下所示：
		/**
		 * Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
		 * // get all metadata properties
		 * route.getMetadata();
		 * // get a single metadata property
		 * route.getMetadata(someKey);
		 */
	}
	
	public void 路由动态更新() {
		//RouteRefreshListener：在GatewayAutoConfiguration中创建
		
		//CachingRouteLocator：在GatewayAutoConfiguration中创建
		//		委托CompositeRouteLocator聚合其他所有RouteLocator的实现类
		//		如RouteDefinitionRouteLocator、DiscoveryClientRouteDefinitionLocator 
		//RouteDefinitionRouteLocator：
		//		委托RouteDefinitionLocator的实现类去获取路由定义，然后将其转换成Route对象
		//		如：DiscoveryClientRouteDefinitionLocator、PropertiesRouteDefinitionLocator、RouteDefinitionRepository
		/**
		 * 更新配置文件SpringCloud会发出RefreshScopeRefreshedEvent
		 * RouteRefreshListener会接收该RefreshScopeRefreshedEvent事件，并且this.publisher.publishEvent(new RefreshRoutesEvent(this));
		 * CachingRouteLocator会接收RefreshRoutesEvent事件并更新路由
		 */
	}
	
	public void HttpTimeoutsConfiguration() {
		//可以为所有路由配置 Http 超时（响应和连接），并为每个特定路由覆盖
		/**
		 * Global timeouts：
		 * To configure Global http timeouts:
		 * connect-timeout： must be specified in milliseconds.
		 * response-timeout： must be specified as a java.time.Duration
		 * spring.cloud.gateway.httpclient:
		 * 		connect-timeout: 1000
		 * 		response-timeout: 5s
		 */
		/**
		 * Per-route timeouts：
		 * To configure per-route timeouts:
		 * connect-timeout must be specified in milliseconds.
		 * response-timeout must be specified in milliseconds.
		 * - id: per_route_timeouts
		 * 	  uri: https://example.org
		 * 	  predicates:
		 * 		- name: Path
		 * 		  args.pattern: /delay/{timeout}
		 * 	  metadata:
		 * 		response-timeout: 200
		 * 		connect-timeout: 200
		 * 使用 Java代码为每条路由超时配置：
		 * https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#per-route-timeouts
		 */
	}
	
	public void 使用JavaAPI设置routes() {
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#fluent-java-routes-api
		/**
		 * @Bean
		 * public RouteLocator customRouteLocator(RouteLocatorBuilder builder, ThrottleGatewayFilterFactory throttle) {
		 * 		...
		 * }
		 */
		//这种风格还允许更多的自定义谓词断言
		//RouteDefinitionLocator bean 定义的谓词使用逻辑与进行组合
		//通过使用 fluent Java API，您可以在 Predicate 类上使用 and()、or() 和 negate() 运算符。
	}
	
	public void discoveryClientRouteDefinitionLocator() {
		//可以将网关配置为基于向 DiscoveryClient 兼容的服务注册表注册的服务创建路由
		//要启用此功能，请设置spring.cloud.gateway.discovery.locator.enabled=true
		//并确保 DiscoveryClient 实现（例如 Netflix Eureka、Consul、Zookeeper、Nacos等）在类路径上并已启用
		
		//DiscoveryLocatorProperties
		//includeExpression：默认为true，pEL 表达式将评估是否在网关中自动集成所有探测到的微服务
		//		即：这个参数配置为true，Gateway则会使用DiscoveryClientRouteDefinitionLocator获取所有的微服务
		//			并自动创建谓词和Filter
		//			serviceInstances = discoveryClient.getServices().flatMap(service -> discoveryClient.getInstances(service).collectList());
		//			discoveryClient为ReactiveCompositeDiscoveryClient的实现
		//			ReactiveCompositeDiscoveryClient则会从NacosReactiveDiscoveryClient和SimpleReactiveDiscoveryClient获取实例			
		//		起作用的地方：DiscoveryClientRouteDefinitionLocator.getRouteDefinitions
		//		如果includeExpression为true，则会调用serviceInstances.filter...基于发现的instances创建谓词和filter
		//urlExpression：默认为"'lb://'+serviceId";
		
		//默认谓词："predicate":"Paths: [/app-user3-impl/**], match trailing slash: true"
		//使用模式 /serviceId/** 定义的路径谓词，其中 serviceId 是来自 DiscoveryClient 的服务的 ID
		
		//默认过滤器：RewritePath /app-user3-impl/?(?<remaining>.*) = '/${remaining}'], order = 1
		//使用正则表达式 /serviceId/?(?<remaining>.*) 和替换 /${remaining} 的重写路径过滤器
		//这会在请求被发送到下游之前从路径中去除服务 ID
		
		//如果要自定义 DiscoveryClient 路由使用的谓词或过滤器
		//请设置 spring.cloud.gateway.discovery.locator.predicates[x] 和 spring.cloud.gateway.discovery.locator.filters[y]
		//这样做时，如果要保留该功能，则需要确保包含前面显示的默认谓词和过滤器
		//以下示例显示了它的样子：
		//spring.cloud.gateway.discovery.locator.predicates[0].name: Path
		//spring.cloud.gateway.discovery.locator.predicates[0].args[pattern]: "'/'+serviceId+'/**'"
		//spring.cloud.gateway.discovery.locator.predicates[1].name: Host
		//spring.cloud.gateway.discovery.locator.predicates[1].args[pattern]: "'**.foo.com'"
		//spring.cloud.gateway.discovery.locator.filters[0].name: CircuitBreaker
		//spring.cloud.gateway.discovery.locator.filters[0].args[name]: serviceId
		//spring.cloud.gateway.discovery.locator.filters[1].name: RewritePath
		//spring.cloud.gateway.discovery.locator.filters[1].args[regexp]: "'/' + serviceId + '/?(?<remaining>.*)'"
		//spring.cloud.gateway.discovery.locator.filters[1].args[replacement]: "'/${remaining}'"
		
		//过滤器链的原理：
		/**
		 * RewritePathGatewayFilterFactory：
		 * 会对请求Gateway的url地址进行替换
		 * 例如请求为：http://localhost:8090/app-user3-impl/api/v1/users?limit=5&offset=1
		 * 获取Path：/app-user3-impl/api/v1/users
		 * 截取NewPath：/api/v1/users
		 * 最终请求变为：http://localhost:8090/api/v1/users?limit=5&offset=1
		 */
		/**
		 * ReactiveLoadBalancerClientFilter：
		 * 根据ServiceId获取实例地址List，并从中选择某一个instance的地址
		 * 获取Path：lb://app-user3-impl/api/v1/users?limit=5&offset=1
		 * 如果url为null，或者schemePrefix不是lb并且url.getScheme()不是lb则略过该Filter
		 * choose(lbRequest, serviceId, supportedLifecycleProcessors)：核心方法，选择DiscoveryClient并获取具体实例
		 * 该方法中：
		 * this.clientFactory.getInstance(serviceId,ReactorServiceInstanceLoadBalancer.class);获取具体的负载均衡器
		 * 这里获得的是RoundRobinLoadBalancer
		 * 
		 * RoundRobinLoadBalancer.choose(Request request)：
		 * ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
		 *		.getIfAvailable(NoopServiceInstanceListSupplier::new);
		 * 该supplier中有DiscoveryClientServiceInstanceListSupplier和ReactiveCompositeDiscoveryClient
		 * 这里会调用例如ReactiveCompositeDiscoveryClient.getInstances(String serviceId)来获取实例列表
		 * 接着会调用getInstanceResponse(List<ServiceInstance> instances)来从实例列表中选择一个具体的实例地址
		 * 根据ServiceInstance中的Host和Port等信息来将URL替换为真实的URL
		 */
		
		//当Nacos服务端的微服务实例发生变化时，Gateway如何感知：
		/**
		 * Gateway在启动的时候以及路由负载均衡的时候，都会使用NacosReactiveDiscoveryClient来获取实例
		 * NacosReactiveDiscoveryClient会调用NacosServiceDiscovery，底层会调用NacosNamingService来获取实例
		 * NacosNamingService中有一个叫serviceInfoHolder的缓存，在应用实例新获取到或者更新后都会放在这里
		 */
		
		//serviceInfoHolder
		/**
		 * NacosNamingService的getServicesOfServer方法每次都会访问Nacos Server来获取一个ListView<String>，String为ServiceId
		 * NacosNamingService的selectInstances(String serviceName,...,boolean subscribe)方法中，subscribe默认为true
		 * 当subscribe为true时，首先会serviceInfoHolder.getServiceInfo(serviceName, groupName, clusterString)来获取缓存中的ServiceInfo
		 * 如果没有找到，则会调用clientProxy.subscribe(serviceName, groupName, clusterString)来获取ServiceInfo
		 * 该clientProxy是NamingClientProxyDelegate的实例
		 * NamingClientProxyDelegate.subscribe(...)方法有一句serviceInfoUpdateService.scheduleUpdateIfAbsent(serviceName, groupName, clusters)
		 * 该方法会条件一个定时任务addTask(new UpdateTask(serviceName, groupName, clusters))，1秒执行1次
		 * UpdateTask会查询此次请求的group@@对应的serviceName，并添加或者更新到serviceInfoHolder
		 * 		每一个请求的group@@serviceName如果serviceInfoHolder中没有，都会添加一个UpdateTask定时任务
		 * 注意：
		 * 		1：当请求一个不存在的group@@ServiceId，NacosNamingService还是会创建对应的UpdateTask并且会创建一个host等
		 * 			都是空的但是包含请求的group和serviceId的ServiceInfo存入serviceInfoHolder
		 * 		2：这里的UpdateTask第一次会1秒执行一次，之后：
		 * 			delayTime = serviceObj.getCacheMillis() * 6，这个cacheMills会发生变化，具体哪里改的，没找到
		 * 			Math.min(delayTime << failCount, DEFAULT_DELAY * 60)
		 */
	}
	
	public void corsConfiguration() {
		//您可以配置网关来控制 CORS 行为
		//“全局”CORS 配置是 URL 模式到 Spring Framework CorsConfiguration 的映射
		//以下示例配置 CORS：
		/**
		 * spring.cloud.gateway.globalcors.cors-configurations:
		 * 		'[/**]':
		 * 		allowedOrigins: "https://docs.spring.io"
		 * 		allowedMethods:
		 * 		- GET
		 */
		
		//add-to-simple-url-handler-mapping：
		//位置：SimpleUrlHandlerMappingGlobalCorsAutoConfiguration
		//开启该配置可以使不在gw路由route predicate配置下的请求也可以使用cors配置
		//由于CORS preflight（预检）请求的method为options，gw通过路由配置无法匹配该options请求，
		//所以默认拒绝options请求并返回403 FORBIDDEN
		//作用：支持浏览器CORS preflight options请求
		//要为某些网关路由谓词未处理的请求提供相同的 CORS 配置
		//请将 spring.cloud.gateway.globalcors.add-to-simple-url-handler-mapping 属性设置为 true
		///当您尝试支持 CORS 预检请求并且您的路由谓词未评估为 true 时，这很有用，因为 HTTP 方法是一个配置

		//配置文件在spring-cloud-gateway-server的GlobalCorsProperties中
	}
	
	public void 监听() {
		new Log().监听();
	}
}
