package org.cat.midd.file3.impl.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.cat.midd.file3.client.constants.AvatarConstants.Status;
import org.cat.midd.file3.client.exception.WikiException;
import org.cat.midd.file3.client.exception.enums.WikiExceptionEnum;
import org.cat.midd.file3.impl.service.WikiService;
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
public class WikiServiceImpl extends AbsMinioService implements WikiService {
	
	@Autowired
	@Qualifier("wikiMinioClient")
	private MinioClient wikiMinioClient;
	
	@Autowired
	@Qualifier("wikiIdGenerator")
	private IIdGenerator idGenerator;
	
	private final String bucketName = "wiki";
	private final String ORIGINAL_FILE_NAME_KEY = "originalFileName";
	private final String STATUS_KEY = "status";

	@Override
	public String insertDocImage(InputStream inputStream, String contentType, String originalDocImageName,
			Long userCode, String fileSuffix)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		
		String saveFileName = generateSaveFileNameForUpload(fileSuffix);
		String objectName = generateDocImageObjectNameForUpload(userCode, saveFileName);
		
		Map<String, String> tagParams = Maps.newHashMap();
		tagParams.put(ORIGINAL_FILE_NAME_KEY, originalDocImageName);
		tagParams.put(STATUS_KEY, Status.VALID.toString());
		
		PutObjectArgs putObjectArgs = PutObjectArgs.builder()
		          .bucket(this.bucketName)
		          .object(objectName)
		          .contentType(contentType)
		          .tags(tagParams)
		          .stream(inputStream, inputStream.available(), -1)
		          .build();
        
//		ObjectWriteResponse objectWriteResponse = avatarMinioClient.putObject(putObjectArgs);
		wikiMinioClient.putObject(putObjectArgs);
			
		return saveFileName;
	}

	@Override
	public InputStream selectDocImage(Long userCode, String docImageName)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {

		String objectName = generateDocImageObjectNameForDownload(userCode, docImageName);
		
//		Map<String, String> headParams = Maps.newHashMap();
//		headParams.put("headKey", "headValue");
//		Map<String, String> queryParams = Maps.newHashMap();
//		queryParams.put("response-content-type", "application/json");
		
		GetObjectTagsArgs getObjectTagsArgs = GetObjectTagsArgs.builder().
				bucket(this.bucketName)
				.object(objectName)
				.build();
		
		Tags tags = wikiMinioClient.getObjectTags(getObjectTagsArgs);
		String status = tags.get().get(STATUS_KEY);
		if(!Status.VALID.equals(status)) {
			throw new WikiException(WikiExceptionEnum.SELECT_USER_DOC_IMAGE_E_STATUS_IS_INVALID);
		}
		
		GetObjectArgs getObjectArgs = GetObjectArgs.builder()
				.bucket(this.bucketName)
				.object(objectName)
//				.extraHeaders(headParams)
//				.extraQueryParams(queryParams)
				.build();
		
		InputStream inputStream = wikiMinioClient.getObject(getObjectArgs);
		
		return inputStream;
	}

	@Override
	public String insertNoteImage(InputStream inputStream, String contentType, String originalNoteImageName,
			Long userCode, String fileSuffix)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		
		String saveFileName = generateSaveFileNameForUpload(fileSuffix);
		String objectName = generateNoteImageObjectNameForUpload(userCode, saveFileName);
		
		Map<String, String> tagParams = Maps.newHashMap();
		tagParams.put(ORIGINAL_FILE_NAME_KEY, originalNoteImageName);
		tagParams.put(STATUS_KEY, Status.VALID.toString());
		
		PutObjectArgs putObjectArgs = PutObjectArgs.builder()
		          .bucket(this.bucketName)
		          .object(objectName)
		          .contentType(contentType)
		          .tags(tagParams)
		          .stream(inputStream, inputStream.available(), -1)
		          .build();
        
//		ObjectWriteResponse objectWriteResponse = avatarMinioClient.putObject(putObjectArgs);
		wikiMinioClient.putObject(putObjectArgs);
			
		return saveFileName;
	}

	@Override
	public InputStream selectNoteImage(Long userCode, String noteImageName)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		
		String objectName = generateNoteImageObjectNameForDownload(userCode, noteImageName);
		
//		Map<String, String> headParams = Maps.newHashMap();
//		headParams.put("headKey", "headValue");
//		Map<String, String> queryParams = Maps.newHashMap();
//		queryParams.put("response-content-type", "application/json");
		
		GetObjectTagsArgs getObjectTagsArgs = GetObjectTagsArgs.builder().
				bucket(this.bucketName)
				.object(objectName)
				.build();
		
		Tags tags = wikiMinioClient.getObjectTags(getObjectTagsArgs);
		String status = tags.get().get(STATUS_KEY);
		if(!Status.VALID.equals(status)) {
			throw new WikiException(WikiExceptionEnum.SELECT_USER_NOTE_IMAGE_E_STATUS_IS_INVALID);
		}
		
		GetObjectArgs getObjectArgs = GetObjectArgs.builder()
				.bucket(this.bucketName)
				.object(objectName)
//				.extraHeaders(headParams)
//				.extraQueryParams(queryParams)
				.build();
		
		InputStream inputStream = wikiMinioClient.getObject(getObjectArgs);
		
		return inputStream;
	}

	@Override
	public String insertQAImage(InputStream inputStream, String contentType, String originalQAImageName, Long userCode,
			String fileSuffix)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		
		String saveFileName = generateSaveFileNameForUpload(fileSuffix);
		String objectName = generateQAImageObjectNameForUpload(userCode, saveFileName);
		
		Map<String, String> tagParams = Maps.newHashMap();
		tagParams.put(ORIGINAL_FILE_NAME_KEY, originalQAImageName);
		tagParams.put(STATUS_KEY, Status.VALID.toString());
		
		PutObjectArgs putObjectArgs = PutObjectArgs.builder()
		          .bucket(this.bucketName)
		          .object(objectName)
		          .contentType(contentType)
		          .tags(tagParams)
		          .stream(inputStream, inputStream.available(), -1)
		          .build();
        
//		ObjectWriteResponse objectWriteResponse = avatarMinioClient.putObject(putObjectArgs);
		wikiMinioClient.putObject(putObjectArgs);
			
		return saveFileName;
	}

	@Override
	public InputStream selectQAImage(Long userCode, String qaImageName)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		
		String objectName = generateQAImageObjectNameForDownload(userCode, qaImageName);
		
//		Map<String, String> headParams = Maps.newHashMap();
//		headParams.put("headKey", "headValue");
//		Map<String, String> queryParams = Maps.newHashMap();
//		queryParams.put("response-content-type", "application/json");
		
		GetObjectTagsArgs getObjectTagsArgs = GetObjectTagsArgs.builder().
				bucket(this.bucketName)
				.object(objectName)
				.build();
		
		Tags tags = wikiMinioClient.getObjectTags(getObjectTagsArgs);
		String status = tags.get().get(STATUS_KEY);
		if(!Status.VALID.equals(status)) {
			throw new WikiException(WikiExceptionEnum.SELECT_USER_QA_IMAGE_E_STATUS_IS_INVALID);
		}
		
		GetObjectArgs getObjectArgs = GetObjectArgs.builder()
				.bucket(this.bucketName)
				.object(objectName)
//				.extraHeaders(headParams)
//				.extraQueryParams(queryParams)
				.build();
		
		InputStream inputStream = wikiMinioClient.getObject(getObjectArgs);
		
		return inputStream;
	}
	
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年12月15日 下午6:11:48
	 * @version 1.0
	 * @description 生成存储器中存储的文件名称  
	 * @param fileSuffix 后罪名
	 * @return
	 */
	private String generateSaveFileNameForUpload(String fileSuffix) {
		String saveFileName = idGenerator.getStrId()+"-"+System.currentTimeMillis()+"."+fileSuffix;
		return saveFileName;
	}
	
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年12月15日 下午6:12:26
	 * @version 1.0
	 * @description 根据userCode和存储器中存储的文件名称生成带路径的objectName 
	 * @param userCode 用户名
	 * @param saveFileName 生成存储器中存储的文件名称  
	 * @return
	 */
	private String generateObjectNameForUpload(Long userCode, String saveFileName) {
		String objectFullName = "user-"+userCode+"/"+saveFileName;
		return objectFullName;
	}
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年12月15日 下午6:20:02
	 * @version 1.0
	 * @description 获取用户文档中的图片的带路径的完整文件名 
	 * @param userCode 用户名
	 * @param saveFileName 生成存储器中存储的文件名称  
	 * @return
	 */
	private String generateDocImageObjectNameForUpload(Long userCode, String saveFileName) {
		String objectFullName = generateObjectNameForUpload(userCode, saveFileName);
		objectFullName = "doc/image/"+objectFullName;
		return objectFullName;
	}
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年12月15日 下午6:20:32
	 * @version 1.0
	 * @description 获取用户笔记中的图片的带路径的完整文件名  
	 * @param userCode 用户名
	 * @param saveFileName 生成存储器中存储的文件名称  
	 * @return
	 */
	private String generateNoteImageObjectNameForUpload(Long userCode, String saveFileName) {
		String objectFullName = generateObjectNameForUpload(userCode, saveFileName);
		objectFullName = "note/image/"+objectFullName;
		return objectFullName;
	}
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年12月15日 下午6:20:32
	 * @version 1.0
	 * @description 获取用户问答中的图片的带路径的完整文件名  
	 * @param userCode 用户名
	 * @param saveFileName 生成存储器中存储的文件名称  
	 * @return
	 */
	private String generateQAImageObjectNameForUpload(Long userCode, String saveFileName) {
		String objectFullName = generateObjectNameForUpload(userCode, saveFileName);
		objectFullName = "qa/image/"+objectFullName;
		return objectFullName;
	}
	
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年12月15日 下午6:14:36
	 * @version 1.0
	 * @description 根据userCode和用户需要获取的文件名生成存储中存着的带路径的完全的文件名  
	 * @param 用户名
	 * @param saveFileName 生成存储器中存储的文件名称
	 * @return
	 */
	private String generateObjectNameForDownload(Long userCode, String saveFileName) {
		String saveFullFileName = generateObjectNameForUpload(userCode, saveFileName);
		return saveFullFileName;
	}
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年12月15日 下午6:22:06
	 * @version 1.0
	 * @description 根据userCode和用户需要获取的用户文档中的图片的文件名生成存储中存着的带路径的完全的文件名   
	 * @param 用户名
	 * @param saveFileName 生成存储器中存储的文件名称
	 * @return
	 */
	private String generateDocImageObjectNameForDownload(Long userCode, String saveFileName) {
		String saveFullFileName = generateObjectNameForDownload(userCode, saveFileName);
		saveFullFileName = "doc/image/"+saveFullFileName;
		return saveFullFileName;
	}
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年12月15日 下午6:22:06
	 * @version 1.0
	 * @description 根据userCode和用户需要获取的用户笔记中的图片的文件名生成存储中存着的带路径的完全的文件名   
	 * @param 用户名
	 * @param saveFileName 生成存储器中存储的文件名称
	 * @return
	 */
	private String generateNoteImageObjectNameForDownload(Long userCode, String saveFileName) {
		String saveFullFileName = generateObjectNameForDownload(userCode, saveFileName);
		saveFullFileName = "note/image/"+saveFullFileName;
		return saveFullFileName;
	}
	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年12月15日 下午6:22:06
	 * @version 1.0
	 * @description 根据userCode和用户需要获取的用户问答中的图片的文件名生成存储中存着的带路径的完全的文件名   
	 * @param 用户名
	 * @param saveFileName 生成存储器中存储的文件名称
	 * @return
	 */
	private String generateQAImageObjectNameForDownload(Long userCode, String saveFileName) {
		String saveFullFileName = generateObjectNameForDownload(userCode, saveFileName);
		saveFullFileName = "qa/image/"+saveFullFileName;
		return saveFullFileName;
	}

}
