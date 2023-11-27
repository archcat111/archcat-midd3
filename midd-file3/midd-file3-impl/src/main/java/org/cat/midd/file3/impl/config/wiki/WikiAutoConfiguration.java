package org.cat.midd.file3.impl.config.wiki;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WikiProperties.class)
public class WikiAutoConfiguration {
	
}
