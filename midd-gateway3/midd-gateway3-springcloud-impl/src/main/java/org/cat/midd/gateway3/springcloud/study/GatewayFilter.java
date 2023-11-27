package org.cat.midd.gateway3.springcloud.study;

public class GatewayFilter {
	
	public void 自定义RoutePredicateFactories() {
		//为了编写RoutePredicate，需要实现 RoutePredicateFactory
		//可以扩展一个名为 AbstractRoutePredicateFactory 的抽象类
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#writing-custom-route-predicate-factories
	}
	
	public void 自定义GatewayFilterFactories() {
		//要编写 GatewayFilter，必须实现 GatewayFilterFactory
		//可以扩展一个名为 AbstractGatewayFilterFactory 的抽象类
		//以下示例显示了如何执行此操作：
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#writing-custom-gatewayfilter-factories
	
		//在配置中命名Custom Filters And References
		//自定义过滤器类名应以 GatewayFilterFactory 结尾
	}
	
	public void 自定义GlobalFilters() {
		//要编写自定义全局过滤器，必须实现 GlobalFilter 接口。 这会将过滤器应用于所有请求
		//以下示例分别显示了如何设置全局前置和后置过滤器：
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#writing-custom-global-filters
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#gateway-combined-global-filter-and-gatewayfilter-ordering
	}
	
	public void 标记为已路由() {
		//网关路由 ServerWebExchange 后，它通过将 gatewayAlreadyRouted 添加到交换属性来将该交换标记为“已路由”
		//一旦请求被标记为已路由，其他路由过滤器将不会再次路由该请求，实质上是跳过过滤器
		//您可以使用一些便捷的方法将交换标记为已路由或检查交换是否已被路由
		
		//ServerWebExchangeUtils.isAlreadyRouted takes a ServerWebExchange object and checks if it has been “routed”.
		//ServerWebExchangeUtils.setAlreadyRouted takes a ServerWebExchange object and marks it as “routed”.
	}
	
	public void 每个XxxPredicateFactory有哪些配置() {
		//每个XxxPredicateFactory都有一个public static class Config的内部类
		//这个Config类的参数就是该谓词可填写的配置
		//例如RemoteAddrRoutePredicateFactory的Config有：
		//private List<String> sources = new ArrayList<>();
		//private RemoteAddressResolver remoteAddressResolver = new RemoteAddressResolver() {};
	}
	
	public void AfterRoutePredicateFactory() {
		//AfterRoutePredicateFactory采用一个参数，一个日期时间（它是一个 java ZonedDateTime）
		//此谓词匹配在指定日期时间之后发生的请求
		//以下示例配置了一个 after 路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - After=2017-01-20T17:42:47.789-07:00[America/Denver]
		 */
		//匹配 2017 年 1 月 20 日 17:42 Mountain Time（丹佛）之后提出的任何请求
	}
	
	public void BeforeRoutePredicateFactory() {
		//BeforeRoutePredicateFactory采用一个参数，一个日期时间（它是一个 java ZonedDateTime）
		//此谓词匹配在指定日期时间之前发生的请求
		//以下示例配置了一个 before 路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Before=2017-01-20T17:42:47.789-07:00[America/Denver]
		 */
		//匹配 2017 年 1 月 20 日 17:42 Mountain Time (Denver) 之前提出的任何请求相匹配。
	}
	
	public void BetweenRoutePredicateFactory() {
		//BetweenRoutePredicateFactory有两个参数，datetime1 和 datetime2
		//它们是 java ZonedDateTime 对象
		//此谓词匹配在 datetime1 和 datetime2 之前发生的请求
		//datetime2 参数必须在 datetime1 之后
		//以下示例配置了一个 between 路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]
		 */
		//匹配 2017 年 1 月 20 日 17:42 山区时间（丹佛）和 2017 年 1 月 21 日 17:42 山区时间（丹佛）之前提出的任何请求
	}
	
	public void CookieRoutePredicateFactory() {
		//CookieRoutePredicateFactory有两个参数，cookie 名称和一个正则表达式（它是一个 Java 正则表达式）
		//此谓词匹配具有给定名称且其值与正则表达式匹配的 cookie
		//以下示例配置 cookie 路由谓词工厂：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Cookie=chocolate, ch.p
		 */
		//此路由匹配具有名为chocolate的 cookie 的请求，该 cookie 的值与 ch.p 正则表达式匹配。
	}
	
	public void HeaderRoutePredicateFactory() {
		//HeaderRoutePredicateFactory采用两个参数，即标头名称和一个正则表达式（它是一个 Java 正则表达式）
		//此谓词与具有给定名称且值与正则表达式匹配的标头匹配
		//以下示例配置了一个标头路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Header=X-Request-Id, \d+
		 */
		//如果请求具有名为 X-Request-Id 且其值与 \d+ 正则表达式匹配的标头（即，它具有一位或多位数字的值）
		//则此路由匹配
	}
	
	public void HostRoutePredicateFactory() {
		//HostRoutePredicateFactory采用一个参数：主机名模式列表
		//该模式是 Ant 风格的模式，带有 . 作为分隔符。 此谓词匹配与模式匹配的 Host 标头
		//以下示例配置主机路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Host=**.somehost.org,**.anotherhost.org
		 */
		//还支持 URI 模板变量（例如 {sub}.myhost.org）
		//如果请求具有值为 www.somehost.org 或 beta.somehost.org 或 www.anotherhost.org 的 Host 标头，则此路由匹配
		
		//此谓词将 URI 模板变量（例如，在前面的示例中定义的 sub）提取为名称和值的映射
		//并将其放置在 ServerWebExchange.getAttributes() 中
		//并使用在 ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE 中定义的键
		//然后这些值可供 GatewayFilter 工厂使用
	}
	
	public void MethodRoutePredicateFactory() {
		//MethodRoutePredicateFactory接受一个方法参数，该参数是一个或多个参数：要匹配的 HTTP 方法
		//以下示例配置方法路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Method=GET,POST
		 */
		//如果请求方法是 GET 或 POST，则此路由匹配
	}
	
	public void PathRoutePredicateFactory() {
		//PathRoutePredicateFactory有两个参数：
		//一个 Spring PathMatcher 模式列表和一个名为 matchTrailingSlash 的可选标志（默认为 true）
		//以下示例配置路径路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Path=/red/{segment},/blue/{segment}
		 */
		//如果请求路径是，例如：/red/1 或 /red/1/ 或 /red/blue 或 /blue/green，则此路由匹配
		//如果 matchTrailingSlash 设置为 false，则请求路径 /red/1/ 将不匹配，默认为true
		
		//此谓词将 URI 模板变量（例如，在前面的示例中定义的segment）提取为名称和值的映射
		//并将其放置在 ServerWebExchange.getAttributes() 中
		//并使用在 ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE 中定义的键
		//然后这些值可供 GatewayFilterFactory使用
		//可以使用一种实用方法（称为 get）来更轻松地访问这些变量。 以下示例显示了如何使用 get 方法：
		/**
		 * Map<String, String> uriVariables = ServerWebExchangeUtils.getPathPredicateVariables(exchange);
		 * String segment = uriVariables.get("segment");
		 */
	}
	
	public void QueryRoutePredicateFactory() {
		//QueryRoutePredicateFactory有两个参数：一个必需的参数和一个可选的 regexp（它是一个 Java 正则表达式）
		//以下示例配置查询路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Query=green
		 */
		//如果请求包含查询参数green，则前面的路由匹配
		/**
		 * - Query=red, gree.
		 */
		//如果请求包含查询参数red，其值与 gree 匹配，则前面的路由匹配
		//因为是正则表达式，所以 green 和 greet 都会匹配
	}
	
	public void RemoteAddrRoutePredicateFactory() {
		//RemoteAddrRoutePredicateFactory采用源列表（最小大小为 1）
		//这些源是 CIDR 表示法（IPv4 或 IPv6）字符串，例如 192.168.0.1/16（其中 192.168.0.1 是 IP 地址，16 是子网掩码 ）
		//以下示例配置 RemoteAddr 路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - RemoteAddr=192.168.1.1/24
		 */
		//如果请求的远程地址是例如 192.168.1.10，则此路由匹配
		
		//修改远程地址的解析方式
		//默认情况下，RemoteAddrRoutePredicateFactory使用来自传入请求的远程地址
		//如果 Spring Cloud Gateway 位于代理层后面，这可能与实际客户端 IP 地址不匹配
		//您可以通过设置自定义 RemoteAddressResolver 来自定义解析远程地址的方式
		//Spring Cloud Gateway 附带一个基于 X-Forwarded-For 标头的非默认远程地址解析器XForwardedRemoteAddressResolver
		
		//XForwardedRemoteAddressResolver 有两个静态构造方法，它们采用不同的安全方法：
		
		//XForwardedRemoteAddressResolver::trustAll 返回一个 RemoteAddressResolver
		//它总是采用在 X-Forwarded-For 标头中找到的第一个 IP 地址
		//这种方法容易受到欺骗，因为恶意客户端可以为 X-Forwarded-For 设置初始值，解析器会接受该值
		
		//XForwardedRemoteAddressResolver::maxTrustedIndex 采用与 Spring Cloud Gateway 前运行的受信任基础设施数量相关的索引
		//例如，如果 Spring Cloud Gateway 只能通过 HAProxy 访问，则应使用值 1
		//如果在访问 Spring Cloud Gateway 之前需要两跳可信基础架构，则应使用值 2
		
		//考虑以下标头值：X-Forwarded-For: 0.0.0.1, 0.0.0.2, 0.0.0.3
		//[Integer.MIN_VALUE,0]：(invalid, IllegalArgumentException during initialization)
		//1：0.0.0.3
		//2：0.0.0.2
		//3：0.0.0.1
		//[4, Integer.MAX_VALUE]：0.0.0.1
		
		//以下示例显示了如何使用 Java 实现相同的配置：
		//RemoteAddressResolver resolver = XForwardedRemoteAddressResolver.maxTrustedIndex(1);
	}
	
	public void WeightRoutePredicateFactory() {
		//WeightRoutePredicateFactory有两个参数：组和权重（一个 int）。 权重是按组计算的
		//以下示例配置权重路由谓词：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: weight_high
		 * 		  uri: https://weighthigh.org
		 * 		  predicates:
		 * 		  - Weight=group1, 8
		 * 		- id: weight_low
		 * 		  uri: https://weightlow.org
		 * 		  predicates:
		 * 		  - Weight=group1, 2
		 */
		//该路由会将约 80% 的流量转发到 weighthigh.org，将约 20% 的流量转发到 weightlow.org
	}
	
	public void iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii(){
		
	}
	
	public void GlobalFilters() {
		
	}
	
	public void RemoveCachedBodyFilter() { //-2147483648
		//移除缓存中的请求体
	}
	
	public void AdaptCachedBodyGlobalFilter() { //-2147482648
		//1：从exchange的attributes中获得cachedRequestBody属性值作为request的body
		//2：必须预设cachedRequestBody属性至attributes中，要手动设置，无法通过配置来设置。
		//		可以在优先级更高的filter中来设置cachedRequestBody。当然也可以自己写一个FilterFactory类来做成配置化
	}
	
	public void NettyWriteResponseFilter() { //-1
		//如果 ServerWebExchangeUtils.CLIENT_RESPONSE_ATTR 交换属性中有 Netty HttpClientResponse
		//则 NettyWriteResponseFilter 运行
		//它在所有其他过滤器完成后运行，并将代理响应写回网关客户端响应
		//（还有一个实验性的 WebClientWriteResponseFilter 执行相同的功能但不需要 Netty。）
	}
	
	public void ForwardPathFilter() { //0
		//条件过滤器
		//只有当请求的header scheme为forward的时候才会发生，否则会忽略没有任何作用
		//当有转发需求的时候会将request的请求path修改，从而修改了请求的目的地址
	}
	
	public void 监控GatewayMetricsFilter() { //0
		//要启用网关指标，请将 spring-boot-starter-actuator 添加为项目依赖项。 
		//然后，默认情况下，只要属性 spring.cloud.gateway.metrics.enabled 未设置为 false，网关指标过滤器就会运行。 
		//此过滤器添加一个默认名为 spring.cloud.gateway.requests(可以修改，GatewayMetricsProperties.prefix) 的计时器指标
		//带有以下标签：
		//		routeId: The route ID
		//		routeUri: The URI to which the API is routed
		//		outcome: The outcome, as classified by HttpStatus.Series.
		//		status: The HTTP status of the request returned to the client.
		//		httpStatusCode: The HTTP Status of the request returned to the client.
		//		httpMethod: The HTTP method used for the request.
		//然后可以从 /actuator/metrics/gateway.requests 中抓取这些指标
		//例如：http://localhost:8090/actuator/metrics/spring.cloud.gateway.requests
		//并且可以与 Prometheus 集成以创建 Grafana 仪表板。
		
		//GatewayMetricsAutoConfiguration、GatewayMetricsProperties
	}
	
	public void RouteToRequestUrlFilter() { //10000
		//通过route自己的filer修改后url为：http://localhost:8090/api/v1/users/loginStatus/
		//通过本过滤器后，转换为：lb://app-user3-impl/api/v1/users/loginStatus/
		
		//如果 ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR 交换属性中有 Route 对象
		//则 RouteToRequestUrlFilter 运行
		//它基于请求 URI 创建一个新的 URI，但使用 Route 对象的 URI 属性进行更新
		//新 URI 放置在 ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR 交换属性中
		
		//如果 URI 具有方案前缀，例如 lb:ws://serviceid
		//则 lb 方案将从 URI 中剥离并放置在 ServerWebExchangeUtils.GATEWAY_SCHEME_PREFIX_ATTR 中
		//以供稍后在过滤器链中使用。
	}
	
	public void ReactiveLoadBalancerClientFilter() { //10150
		//ReactiveLoadBalancerClientFilter 在名为 ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR 的交换属性中查找 URI
		//如果 URL 具有 lb 方案（例如 lb://myservice）
		//它使用 Spring Cloud ReactorLoadBalancer 将名称（在本例中为 myservice）解析为实际的主机和端口
		//并替换同一属性中的 URI
		//未修改的原始 URL 将附加到 ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR 属性中的列表
		//过滤器还查看 ServerWebExchangeUtils.GATEWAY_SCHEME_PREFIX_ATTR 属性以查看它是否等于 lb
		//如果是，则适用相同的规则
		//以下清单配置了 ReactiveLoadBalancerClientFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: myRoute
		 * 		  uri: lb://service
		 * 		  predicates:
		 * 		  - Path=/service/**
		 */
		
		//默认情况下，当 ReactorLoadBalancer 找不到服务实例时，会返回 503
		//您可以通过设置 spring.cloud.gateway.loadbalancer.use404=true 来配置网关以返回 404
	}
	
	public void WebsocketRoutingFilter() { //2147483646
		//如果位于 ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR 交换属性中的 URL 具有 ws 或 wss 方案
		//则 websocket 路由过滤器运行
		//它使用 Spring WebSocket 基础设施向下游转发 websocket 请求
		
		//您可以通过在 URI 前加上 lb 来对 websocket 进行负载平衡，例如 lb:ws://serviceid
		
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#the-websocket-routing-filter
	}
	
	public void ForwardRoutingFilter() { //2147483647
		//ForwardRoutingFilter 在交换属性 ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR 中查找 URI
		//如果 URL 有转发方案（例如 forward:///localendpoint），它使用 Spring DispatcherHandler 来处理请求
		//请求 URL 的路径部分被转发 URL 中的路径覆盖
		//未修改的原始 URL 将附加到 ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR 属性中的列表
	}
	
	public void NettyRoutingFilter() {
		//如果位于 ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR 交换属性中的 URL 具有 http 或 https 方案
		//则 Netty 路由过滤器运行
		//它使用 Netty HttpClient 发出下游代理请求
		//响应放在 ServerWebExchangeUtils.CLIENT_RESPONSE_ATTR 交换属性中，以供以后的过滤器使用
		//（还有一个实验性的 WebClientHttpRoutingFilter 执行相同的功能但不需要 Netty。）
	}
	
	public void iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii(){
		
	}
	
	public void HttpHeadersFilters() {
		
	}
	
	public void ForwardedHeadersFilter() {
		//Forwarded Headers Filter 创建一个 Forwarded 头来发送给下游服务
		//它将当前请求的 Host 标头、方案和端口添加到任何现有的 Forwarded 标头。
	}
	
	public void RemoveHopByHopHeadersFilter() {
		//RemoveHopByHop 标头过滤器从转发的请求中删除标头。 删除的默认标头列表来自 IETF
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#removehopbyhop-headers-filter
	}
	
	public void XForwardedHeadersFilter() {
		//XForwarded 标头过滤器创建各种 X-Forwarded-* 标头以发送到下游服务
		//它使用当前请求的 Host 标头、方案、端口和路径来创建各种标头
		
		//可以通过以下布尔属性控制单个标题的创建（默认为 true）：
		//spring.cloud.gateway.x-forwarded.for-enabled
		//spring.cloud.gateway.x-forwarded.host-enabled
		//spring.cloud.gateway.x-forwarded.port-enabled
		//spring.cloud.gateway.x-forwarded.proto-enabled
		//spring.cloud.gateway.x-forwarded.prefix-enabled
		
		//附加多个标题可以由以下布尔属性控制（默认为 true）：
		//spring.cloud.gateway.x-forwarded.for-append
		//spring.cloud.gateway.x-forwarded.host-append
		//spring.cloud.gateway.x-forwarded.port-append
		//spring.cloud.gateway.x-forwarded.proto-append
		//spring.cloud.gateway.x-forwarded.prefix-append
	}
	
	public void iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii(){
		
	}
	
	public void DefaultFilters() {
		//要添加过滤器并将其应用于所有路由，您可以使用 spring.cloud.gateway.default-filters\
		//此属性采用过滤器列表
		//以下清单定义了一组默认过滤器：
		/**
		 * spring.cloud.gateway.default-filters:
		 * 		- AddResponseHeader=X-Response-Default-Red, Default-Blue
		 * 		- PrefixPath=/httpbin
		 */
	}
	
	public void iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii(){
		
	}
	
	
	public void AddRequestHeaderGatewayFilterFactory() {
		//AddRequestHeaderGatewayFilterFactory的参数有名称和值
		//以下示例配置一个 AddRequestHeaderGatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - AddRequestHeader=X-Request-red, blue
		 */
		//此配置将X-Request-red:blue 标头添加到所有匹配请求的下游请求标头中
	}
	
	public void AddRequestParameterGatewayFilterFactory() {
		//AddRequestParameterGatewayFilterFactory的参数有名称和值
		//以下示例配置一个 AddRequestParameterGatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - AddRequestParameter=red, blue
		 */
		//这会将 red=blue 添加到所有匹配请求的下游请求的查询字符串中
		
		//AddRequestParameter知道用于匹配路径或主机的 URI 变量
		//URI 变量可以在值中使用并在运行时扩展
		//以下示例配置使用变量的 AddRequestParameterGatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Host: {segment}.myhost.org
		 * 		  filters:
		 * 		  - AddRequestParameter=red, blue
		 */
	}
	
	public void AddResponseHeaderGatewayFilterFactory() {
		//AddResponseHeaderGatewayFilterFactory的参数有名称和值
		//以下示例配置一个 AddResponseHeaderGatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - AddResponseHeader=X-Response-Red, Blue
		 */
		//这会将 X-Response-Foo:Bar 标头添加到所有匹配请求的下游响应标头中
		
		//AddResponseHeader 知道用于匹配路径或主机的 URI 变量
		//URI 变量可以在值中使用并在运行时扩展
		//以下示例配置使用变量的 AddResponseHeaderGatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Host: {segment}.myhost.org
		 * 		  filters:
		 * 		  - AddResponseHeader=foo, bar-{segment}
		 */
	}
	
	public void DedupeResponseHeaderGatewayFilterFactory() {
		//DedupeResponseHeaderGatewayFilterFactory有名称参数和可选策略参数
		//name 可以包含以空格分隔的标题名称列表
		//以下示例配置了 DedupeResponseHeaderGatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - DedupeResponseHeader=Access-Control-Allow-Credentials Access-Control-Allow-Origin
		 */
		//在网关 CORS 逻辑和下游逻辑都添加它们的情况下
		//这将删除 Access-Control-Allow-Credentials 和 Access-Control-Allow-Origin 响应标头的重复值
		//DedupeResponseHeader 过滤器还接受可选的策略参数
		//接受的值为 RETAIN_FIRST（默认）、RETAIN_LAST 和 RETAIN_UNIQUE
	}
	
	public void CircuitBreakerGatewayFilterFactory() {
		//SpringCloudCircuitBreakerGatewayFilter使用 Spring Cloud CircuitBreaker API 将网关路由包装在断路器中
		//Spring Cloud CircuitBreaker 支持多个可与 Spring Cloud Gateway 一起使用的库
		//Spring Cloud 开箱即用地支持 Resilience4J
		
		//要启用 Spring Cloud CircuitBreaker 过滤器
		//您需要将 spring-cloud-starter-circuitbreaker-reactor-resilience4j 放在类路径上
		//以下示例配置 Spring Cloud CircuitBreaker GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - CircuitBreaker=myCircuitBreaker
		 */
		//要配置断路器，请参阅您正在使用的底层断路器实现的配置
		//https://cloud.spring.io/spring-cloud-circuitbreaker/reference/html/spring-cloud-circuitbreaker.html
		
		//Spring Cloud CircuitBreaker 过滤器也可以接受一个可选的 fallbackUri 参数
		//目前，只有forward: 支持schemed URIs
		//如果调用了fallback，则将请求转发到URI匹配的控制器
		//以下示例配置了这样的fallback：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Path=/consumingServiceEndpoint
		 * 		  filters:
		 * 		  - name: CircuitBreaker
		 * 			args:
		 * 				name: myCircuitBreaker
		 * 				fallbackUri: forward:/inCaseOfFailureUseThis
		 * 		  - RewritePath=/consumingServiceEndpoint, /backingServiceEndpoint
		 */
		//下面的清单在 Java 中做同样的事情：
		/**
		 * @Bean
		 * public RouteLocator routes(RouteLocatorBuilder builder) {
		 * 		return builder.routes()
		 * 			.route("circuitbreaker_route", r -> r.path("/consumingServiceEndpoint")
		 * 				.filters(f -> f.circuitBreaker(c -> c.name("myCircuitBreaker").fallbackUri("forward:/inCaseOfFailureUseThis"))
		 * 					.rewritePath("/consumingServiceEndpoint", "/backingServiceEndpoint")).uri("lb://backing-service:8088")
		 * 			.build();
		 * }
		 */
		//更多信息：
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#spring-cloud-circuitbreaker-filter-factory
		//更多的配置项
		//如：根据状态码进行熔断等
	}
	
	public void FallbackHeadersGatewayFilterFactory() {
		//FallbackHeadersGatewayFilterFactory允许您在转发到外部应用程序中的 fallbackUri 的请求的标头中添加 Spring Cloud CircuitBreaker 执行异常详细信息
		//如以下场景所示：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: ingredients
		 * 		  uri: lb://ingredients
		 * 		  predicates:
		 * 		  - Path=//ingredients/**
		 * 		  filters:
		 * 		  - name: CircuitBreaker
		 * 			args:
		 * 				name: fetchIngredients
		 * 				fallbackUri: forward:/fallback
		 * 		- id: ingredients-fallback
		 * 		  uri: http://localhost:9994
		 * 		  predicates:
		 * 		  - Path=/fallback
		 * 		  filters:
		 * 		  - name: FallbackHeaders
		 * 			args:
		 * 				executionExceptionTypeHeaderName: Test-Header
		 */
		//在此示例中，在运行断路器时发生执行异常后，请求将转发到在 localhost:9994 上运行的应用程序中的回退端点或处理程序
		//FallbackHeaders 过滤器将带有异常类型、消息和（如果可用）根本原因异常类型和消息的标头添加到该请求中
		
		//您可以通过设置以下参数的值（显示为默认值）来覆盖配置中标头的名称：
		//executionExceptionTypeHeaderName ("Execution-Exception-Type")
		//executionExceptionMessageHeaderName ("Execution-Exception-Message")
		//rootCauseExceptionTypeHeaderName ("Root-Cause-Exception-Type")
		//rootCauseExceptionMessageHeaderName ("Root-Cause-Exception-Message")
		//更多：
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#spring-cloud-circuitbreaker-filter-factory
	}
	
	public void MapRequestHeaderGatewayFilterFactory() {
		//MapRequestHeaderGatewayFilterFactory采用 fromHeader 和 toHeader 参数
		//它创建一个新的命名标头（toHeader），并从传入的 http 请求中从现有的命名标头（fromHeader）中提取值
		//如果输入标头不存在，则过滤器没有影响
		//如果新的命名标头已经存在，则其值将使用新值进行扩充
		//以下示例配置 MapRequestHeader：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - MapRequestHeader=Blue, X-Request-Red
		 */
		//这会将 X-Request-Red:<values> 标头添加到下游请求中，其中包含来自传入 HTTP 请求的 Blue 标头的更新值。
	}
	
	public void PrefixPathGatewayFilterFactory() {
		//PrefixPathGatewayFilterFactory采用单个前缀参数
		//以下示例配置 PrefixPath GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - PrefixPath=/mypath
		 */
		//这将为所有匹配请求的路径添加前缀 /mypath。 因此，对 /hello 的请求将被发送到 /mypath/hello。
	}
	
	public void PreserveHostHeaderGatewayFilterFactory() {
		//PreserveHostHeaderGatewayFilterFactory没有参数
		//此过滤器设置路由过滤器检查的请求属性，以确定是否应发送原始主机头，而不是由 HTTP 客户端确定的主机头
		//以下示例配置 PreserveHostHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - PreserveHostHeader
		 */
	}
	
	public void RequestRateLimiterGatewayFilterFactory() {
		//RequestRateLimiterGatewayFilterFactory使用 RateLimiter 实现来确定是否允许当前请求继续
		//如果不是，则返回 HTTP 429 - Too Many Requests（默认情况下）状态
		
		//此过滤器采用可选的 keyResolver 参数和特定于速率限制器的参数（本节稍后描述）
		//keyResolver 是一个实现 KeyResolver 接口的 bean
		//在配置中，使用 SpEL 按名称引用 bean
		//#{@myKeyResolver} 是一个引用名为 myKeyResolver 的 bean 的 SpEL 表达式
		//KeyResolver 接口让可插拔策略派生出限制请求的密钥。 在未来的里程碑版本中，将会有一些 KeyResolver 实现
		//KeyResolver 的默认实现是 PrincipalNameKeyResolver，它从 ServerWebExchange 中检索 Principal 并调用 Principal.getName()
		
		//默认情况下，如果 KeyResolver 未找到密钥，则拒绝请求
		//您可以通过设置 spring.cloud.gateway.filter.request-rate-limiter.deny-empty-key（true 或 false）和 spring.cloud.gateway.filter.request-rate-limiter.empty-key 来调整此行为 -状态码属性。
	
		//redis-ratelimiter
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#the-redis-ratelimiter
	}
	
	public void RedirectToGatewayFilterFactory() {
		//RedirectToGatewayFilterFactory接受两个参数，status 和 url
		//status 参数应该是一个 300 系列重定向 HTTP 代码，例如 301
		//url 参数应该是一个有效的 URL，这是 Location 标头的值
		//对于相对重定向，您应该使用 uri:no://op 作为路由定义的 uri
		//以下清单配置了一个 RedirectTo GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - RedirectTo=302, https://acme.org
		 */
		//这将发送带有 Location:https://acme.org 标头的状态 302 以执行重定向
	}
	
	public void RemoveRequestHeaderGatewayFilterFactory() {
		//RemoveRequestHeaderGatewayFilterFactory采用名称参数
		//它是要删除的标头的名称
		//以下清单配置了 RemoveRequestHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - RemoveRequestHeader=X-Request-Foo
		 */
		//这会在 X-Request-Foo 标头被发送到下游之前删除它
	}
	
	public void RemoveResponseHeaderGatewayFilterFactory() {
		//RemoveResponseHeaderGatewayFilterFactory采用名称参数
		//它是要删除的标头的名称
		//以下清单配置了 RemoveResponseHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - RemoveResponseHeader=X-Response-Foo
		 */
		//这将在返回给网关客户端之前从响应中删除 X-Response-Foo 标头
		
		//要删除任何类型的敏感标头，您应该为您可能想要这样做的任何路由配置此过滤器
		//此外，您可以使用 spring.cloud.gateway.default-filters 配置此过滤器一次，并将其应用于所有路由
	}
	
	public void RemoveRequestParameterGatewayFilterFactory() {
		//RemoveRequestParameterGatewayFilterFactory采用名称参数
		//它是要删除的查询参数的名称
		//以下示例配置了 RemoveRequestParameter GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: after_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - RemoveRequestParameter=red
		 */
		//这将在将参数red发送到下游之前将其删除
	}
	
	public void RewritePathGatewayFilterFactory() {
		//RewritePathGatewayFilterFactory采用路径正则表达式参数和替换参数
		//这使用 Java 正则表达式以灵活的方式重写请求路径
		//以下清单配置了 RewritePath GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: rewritepath_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Path=/red/**
		 * 		  filters:
		 * 		  - RewritePath=/red/?(?<segment>.*), /$\{segment}
		 */
		//对于 /red/blue 的请求路径，这会在发出下游请求之前将路径设置为 /blue
		//请注意，由于 YAML 规范，应将 $ 替换为 $\。
	}
	
	public void RewriteLocationResponseHeaderGatewayFilterFactory() {
		//RewriteLocationResponseHeaderGatewayFilterFactory修改 Location 响应标头的值
		//通常是为了摆脱后端特定的细节
		//它采用stripVersionMode、locationHeaderName、hostValue 和protocolsRegex 参数
		//以下清单配置了 RewriteLocationResponseHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: rewritepath_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - RewriteLocationResponseHeader=AS_IN_REQUEST, Location, ,
		 */
		//例如POST api.example.com/some/object/name的请求
		//object-service.prod.example.net/v2/some/object/id的Location响应头值
		//被重写为api.example.com/some/object/id
		//更多详情：
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#rewritelocationresponseheader-gatewayfilter-factory
	}
	
	public void RewriteResponseHeaderGatewayFilterFactory() {
		//RewriteResponseHeaderGatewayFilterFactory采用名称、正则表达式和替换参数
		//它使用 Java 正则表达式以灵活的方式重写响应标头值
		//以下示例配置了 RewriteResponseHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: rewritepath_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - RewriteResponseHeader=X-Response-Red, , password=[^&]+, password=***
		 */
		//对于 /42?user=ford&password=omg!what&flag=true 的header值
		//在发出下游请求后将其设置为 /42?user=ford&password=***&flag=true
		//由于 YAML 规范，您必须使用 $\ 来表示 $。
	}
	
	public void SaveSessionGatewayFilterFactory() {
		//SaveSessionGatewayFilterFactory在将请求转发到下游之前强制执行 WebSession::save 操作
		//这在使用诸如带有惰性数据存储的 Spring Session 之类的东西时特别有用
		//并且您需要确保在进行转发调用之前已保存会话状态
		//以下示例配置了 SaveSession GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: save_session
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Path=/foo/**
		 * 		  filters:
		 * 		  - SaveSession
		 */
		//如果您将 Spring Security 与 Spring Session 集成并希望确保安全详细信息已转发到远程进程，这很关键
	}
	
	public void SecureHeadersGatewayFilterFactory() {
		//根据这篇博文中的建议，SecureHeadersGatewayFilterFactory向响应中添加了许多标头
		//https://blog.appcanary.com/2017/http-security-headers.html
		//更多信息：
		//https://docs.spring.io/spring-cloud-gateway/docs/3.0.4/reference/html/#the-secureheaders-gatewayfilter-factory
	}
	
	public void SetPathGatewayFilterFactory() {
		//SetPathGatewayFilterFactory采用路径模板参数
		//它通过允许路径的模板化段提供了一种操作请求路径的简单方法
		//这使用来自 Spring Framework 的 URI 模板。 允许多个匹配段
		//以下示例配置 SetPath GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: setpath_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Path=/red/{segment}
		 * 		  filters:
		 * 		  - SetPath=/{segment}
		 */
		//对于 /red/blue 的请求路径，这会在发出下游请求之前将路径设置为 /blue
	}
	
	public void SetRequestHeaderGatewayFilterFactory() {
		//SetRequestHeaderGatewayFilterFactory采用名称和值参数
		//以下清单配置了一个 SetRequestHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: setrequestheader_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - SetRequestHeader=X-Request-Red, Blue
		 */
		//此 GatewayFilter 用给定名称替换（而不是添加）所有标头
		//因此，如果下游服务器以 X-Request-Red:1234 响应，这将被 X-Request-Red:Blue 替换，这是下游服务将收到的
		
		//SetRequestHeader 知道用于匹配路径或主机的 URI 变量
		//URI 变量可以在值中使用并在运行时扩展
		//以下示例配置使用变量的 SetRequestHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: setrequestheader_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Host: {segment}.myhost.org
		 * 		  filters:
		 * 		  - SetRequestHeader=foo, bar-{segment}
		 */
	}
	
	public void SetResponseHeaderGatewayFilterFactory() {
		//SetResponseHeaderGatewayFilterFactory采用名称和值参数
		//以下清单配置了一个 SetResponseHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: setresponseheader_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - SetResponseHeader=X-Response-Red, Blue
		 */
		//此 GatewayFilter 用给定名称替换（而不是添加）所有标头
		//因此，如果下游服务器以 X-Response-Red:1234 进行响应，则会将其替换为 X-Response-Red:Blue
		//这是网关客户端将收到的内容。
		
		//SetResponseHeader 知道用于匹配路径或主机的 URI 变量
		//URI 变量可以在值中使用，并将在运行时扩展
		//以下示例配置使用变量的 SetResponseHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: setresponseheader_route
		 * 		  uri: https://example.org
		 * 		  predicates:
		 * 		  - Host: {segment}.myhost.org
		 * 		  filters:
		 * 		  - SetResponseHeader=foo, bar-{segment}
		 */
	}
	
	public void SetStatusGatewayFilterFactory() {
		//SetStatusGatewayFilterFactory采用单个参数 status
		//它必须是有效的 Spring HttpStatus
		//它可能是整数值 404 或枚举的字符串表示形式：NOT_FOUND
		//以下清单配置了 SetStatus GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: setstatusstring_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - SetStatus=BAD_REQUEST
		 * 		- id: setstatusint_route
		 * 		  uri: https://example.org
		 * 		  filters:
		 * 		  - SetStatus=401
		 */
		//无论哪种情况，响应的 HTTP 状态都设置为 401
		
		//您可以将 SetStatus GatewayFilter 配置为在响应的标头中返回来自代理请求的原始 HTTP 状态代码
		//如果配置了以下属性，则标头将添加到响应中：
		/**
		 * spring.cloud.gateway:
		 * 		set-status:
		 * 			original-status-header-name: original-http-status
		 */
	}
	
	public void StripPrefixGatewayFilterFactory() {
		//StripPrefixGatewayFilterFactory采用一个参数，parts
		//parts 参数指示在将请求发送到下游之前要从请求中剥离的路径中的部分数
		//以下清单配置了 StripPrefix GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: nameRoot
		 * 		  uri: https://nameservice
		 * 		  predicates:
		 * 		  - Path=/name/**
		 * 		  filters:
		 * 		  - StripPrefix=2
		 */
		//当通过网关向 /name/blue/red 发出请求时，对 nameservice 的请求看起来像 nameservice/red
	}
	
	public void RetryGatewayFilterFactory() {
		//RetryGatewayFilterFactory支持以下参数：
		//retries: 应该尝试的重试次数
		//statuses: 应该重试的 HTTP 状态码，使用 org.springframework.http.HttpStatus 表示
		//methods: 应该重试的HTTP方法，用org.springframework.http.HttpMethod表示
		//series: 需要重试的一系列状态码，使用 org.springframework.http.HttpStatus.Series 表示
		//exceptions: 应重试的抛出异常列表
		//backoff: 为重试配置的指数退避
		//		在 firstBackoff * (factor ^ n) 的退避间隔后执行重试，其中 n 是迭代
		//		如果配置了 maxBackoff，则应用的最大退避限制为 maxBackoff
		//		如果 basedOnPreviousValue 为 true，则使用 prevBackoff * factor 计算退避
		//如果启用，则为重试过滤器配置以下默认值：
		//		retries: Three times
		//		series: 5XX series
		//		methods: GET method
		//		exceptions: IOException and TimeoutException
		//		backoff: disabled
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: retry_test
		 * 		  uri: http://localhost:8080/flakey
		 * 		  predicates:
		 * 		  - Host=*.retry.com
		 * 		  filters:
		 * 		  - name: Retry
		 * 			args:
		 * 				retries: 3
		 * 				statuses: BAD_GATEWAY
		 * 				methods: GET,POST
		 * 				backoff:
		 * 					firstBackoff: 10ms
		 * 					maxBackoff: 50ms
		 * 					factor: 2
		 * 					basedOnPreviousValue: false
		 */
	}
	
	public void RequestSizeGatewayFilterFactory() {
		//当请求大小大于允许的限制时，RequestSizeGatewayFilterFactory可以限制请求到达下游服务
		//过滤器采用 maxSize 参数
		//maxSize 是一种“DataSize”类型，因此可以将值定义为一个数字，后跟一个可选的 DataUnit 后缀
		//例如“KB”或“MB”。 字节的默认值为“B”
		//它是以字节为单位定义的请求的允许大小限制
		//以下清单配置了 RequestSize GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: request_size_route
		 * 		  uri: http://localhost:8080/upload
		 * 		  predicates:
		 * 		  - Path=/upload
		 * 		  filters:
		 * 		  - name: RequestSize
		 * 			args:
		 * 				maxSize: 5000000
		 */
		//当请求因大小而被拒绝时，RequestSize GatewayFilter 工厂将响应状态设置为 413 Payload Too Large
		//并带有额外的标头 errorMessage。 以下示例显示了这样的错误消息：
		//errorMessage` : `Request size is larger than permissible limit. Request size is 6.0 MB where permissible limit is 5.0 MB
	}
	
	public void SetRequestHostHeaderGatewayFilterFactory() {
		//在某些情况下，可能需要覆盖主机标头
		//在这种情况下，SetRequestHostHeaderGatewayFilterFactory可以用指定的值替换现有的主机头
		//过滤器采用主机参数
		//以下清单配置了 SetRequestHostHeader GatewayFilter：
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: set_request_host_header_route
		 * 		  uri: http://localhost:8080/headers
		 * 		  predicates:
		 * 		  - Path=/headers
		 * 		  filters:
		 * 		  - name: SetRequestHostHeader
		 * 			args:
		 * 				host: example.org
		 */
	}
	
	public void 编写一个可以处理RequestBody的Filter() {
		/**
		 * @Bean
		 * public RouteLocator routes(RouteLocatorBuilder builder) {
		 * 		return builder.routes()
		 * 			.route("rewrite_request_obj", r -> r.host("*.rewriterequestobj.org")
		 * 				.filters(f -> f.prefixPath("/httpbin")
		 * 					.modifyRequestBody(String.class, Hello.class, MediaType.APPLICATION_JSON_VALUE,
		 * 						(exchange, s) -> return Mono.just(new Hello(s.toUpperCase())))).uri(uri))
		 * 			.build();
		 * }
		 * static class Hello {
		 * 		String message;
		 * }
		 */
	}
	
	public void 编写一个可以处理ResponseBody的Filter() {
		/**
		 * @Bean
		 * public RouteLocator routes(RouteLocatorBuilder builder) {
		 * 		return builder.routes()
		 * 			.route("rewrite_response_upper", r -> r.host("*.rewriteresponseupper.org")
		 * 				.filters(f -> f.prefixPath("/httpbin")
		 * 					.modifyResponseBody(String.class, String.class,
		 * 						(exchange, s) -> Mono.just(s.toUpperCase()))).uri(uri))
		 * 			.build();
		 * }
		 */
	}
	
	public void TokenRelayGatewayFilterFactory() {
		//TokenRelay是 OAuth2 消费者充当客户端并将传入令牌转发给传出资源请求的地方
		//消费者可以是纯客户端（如 SSO 应用程序）或资源服务器
		
		//Spring Cloud Gateway 可以将 OAuth2 访问令牌向下游转发到它所代理的服务
		//要将此功能添加到网关，您需要像这样添加 TokenRelayGatewayFilterFactory：
		/**
		 * @Bean
		 * public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		 * 		return builder.routes()
		 * 			.route("resource", r -> r.path("/resource")
		 * 				.filters(f -> f.tokenRelay())
		 * 					.uri("http://localhost:9000"))
		 * 			.build();
		 * }
		 */
		/**
		 * spring.cloud.gateway.routes:
		 * 		- id: resource
		 * 		  uri: http://localhost:9000
		 * 		  predicates:
		 * 		  - Path=/resource
		 * 		  filters:
		 * 		  - TokenRelay=
		 */
	}
}
