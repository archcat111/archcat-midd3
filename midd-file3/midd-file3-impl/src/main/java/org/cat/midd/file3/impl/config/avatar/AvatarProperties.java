package org.cat.midd.file3.impl.config.avatar;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "cat.midd.file.avatar")
@Getter
@Setter
public class AvatarProperties {
	
	private AvatarUploadProperties upload;
}
