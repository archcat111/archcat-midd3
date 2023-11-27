package org.cat.midd.file3.impl.config.wiki;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "cat.midd.file.wiki")
@Getter
@Setter
public class WikiProperties {
	private WikiUploadProperties upload;
}
