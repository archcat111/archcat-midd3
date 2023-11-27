package org.cat.midd.file3.impl.config.avatar;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AvatarProperties.class)
public class AvatarAutoConfiguration {
	
//	@Bean
//	public AvatarProperties avatarProperties() {
//		AvatarProperties avatarProperties = new AvatarProperties();
//		return avatarProperties;
//	}
}
