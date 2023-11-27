package org.cat.midd.file3.impl.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.cat.midd.file3.client.constants.AvatarConstants.Status;
import org.cat.midd.file3.client.exception.AvatarException;
import org.cat.midd.file3.client.exception.enums.AvatarExceptionEnum;
import org.cat.midd.file3.impl.service.AvatarService;
import org.cat.midd.file3.impl.service.abs.AbsMinioService;
import org.cat.support.id3.generator.IIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import io.minio.GetObjectArgs;
import io.minio.GetObjectTagsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Tags;

@Service
public class AvatarServiceImpl extends AbsMinioService implements AvatarService {
	
	@Autowired
	@Qualifier("avatarMinioClient")
	private MinioClient avatarMinioClient;
	
	@Autowired
	@Qualifier("avararIdGenerator")
	private IIdGenerator idGenerator;
	
	private final String bucketName = "avatar";
	private final String ORIGINAL_FILE_NAME_KEY = "originalFileName";
	private final String STATUS_KEY = "status";
	
	@Override
	public String insertAvatar(InputStream inputStream, String contentType, String originalFileName, Long userCode, String fileSuffix) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		String avatarFileName = generateAvatarFileNameForUpload(fileSuffix);
		String objectName = generateObjectNameForUpload(userCode, avatarFileName);
		
		Map<String, String> tagParams = Maps.newHashMap();
		tagParams.put(ORIGINAL_FILE_NAME_KEY, originalFileName);
		tagParams.put(STATUS_KEY, Status.VALID.toString());
		
		PutObjectArgs putObjectArgs = PutObjectArgs.builder()
		          .bucket(this.bucketName)
		          .object(objectName)
		          .contentType(contentType)
		          .tags(tagParams)
		          .stream(inputStream, inputStream.available(), -1)
		          .build();
        
//		ObjectWriteResponse objectWriteResponse = avatarMinioClient.putObject(putObjectArgs);
		 avatarMinioClient.putObject(putObjectArgs);
			
		return avatarFileName;
	}
	
	@Override
	public InputStream selectAvatar(Long userCode, String avatarFileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		String objectName = generateObjectNameForDownload(userCode, avatarFileName);
		
//		Map<String, String> headParams = Maps.newHashMap();
//		headParams.put("headKey", "headValue");
//		Map<String, String> queryParams = Maps.newHashMap();
//		queryParams.put("response-content-type", "application/json");
		
		GetObjectTagsArgs getObjectTagsArgs = GetObjectTagsArgs.builder().
				bucket(this.bucketName)
				.object(objectName)
				.build();
		
		Tags tags = avatarMinioClient.getObjectTags(getObjectTagsArgs);
		String status = tags.get().get(STATUS_KEY);
		if(!Status.VALID.equals(status)) {
			throw new AvatarException(AvatarExceptionEnum.SELECT_USER_AVATAR_E_STATUS_IS_INVALID);
		}
		
		GetObjectArgs getObjectArgs = GetObjectArgs.builder()
				.bucket(this.bucketName)
				.object(objectName)
//				.extraHeaders(headParams)
//				.extraQueryParams(queryParams)
				.build();
		
		InputStream inputStream = avatarMinioClient.getObject(getObjectArgs);
		
		return inputStream;
	}
	
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年7月29日 下午4:25:05
	 * @version 1.0
	 * @description 生成存储器中存储的文件名称 
	 * @param fileSuffix
	 * @return
	 */
	private String generateAvatarFileNameForUpload(String fileSuffix) {
		String saveFileName = idGenerator.getStrId()+"-"+System.currentTimeMillis()+"."+fileSuffix;
		return saveFileName;
	}
	
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年7月28日 下午6:46:18
	 * @version 1.0
	 * @description 根据userCode和存储器中存储的文件名称生成完整带路径的objectName
	 * @param userCode
	 * @param saveFileName
	 * @return
	 */
	private String generateObjectNameForUpload(Long userCode, String avatarFileName) {
		String objectFullName = "user-"+userCode+"/"+avatarFileName;
		return objectFullName;
	}
	
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年7月28日 下午6:46:43
	 * @version 1.0
	 * @description 根据userCode和用户需要获取的文件名生成存储中存着完全的文件名 
	 * @param userCode
	 * @param avatarFileName
	 * @return
	 */
	private String generateObjectNameForDownload(Long userCode, String avatarFileName) {
		String saveFullFileName = generateObjectNameForUpload(userCode, avatarFileName);
		return saveFullFileName;
	}
}
