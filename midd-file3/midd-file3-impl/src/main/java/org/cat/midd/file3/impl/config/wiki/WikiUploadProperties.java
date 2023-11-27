package org.cat.midd.file3.impl.config.wiki;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.util.unit.DataSize;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WikiUploadProperties {
	private boolean allowUploadWikiImage = true;
	private List<String> allowWikiImageSuffixes = Lists.newArrayList("jpg","jpeg","png");
	private List<String> allowWikiImageContentTypes = Lists.newArrayList(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE);
	private DataSize allowWikiImageSize = DataSize.ofMegabytes(5); //允许5M
}
