package org.cat.midd.file3.impl.config.avatar;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.util.unit.DataSize;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvatarUploadProperties {

	private boolean allowUploadAvatar = true;
	private List<String> allowAvatarFileSuffixes = Lists.newArrayList("jpg","jpeg","png");
	private List<String> allowAvatarFileContentTypes = Lists.newArrayList(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE);
	private DataSize allowAvatarFileSize = DataSize.ofMegabytes(2); //允许2M
	
	
}
