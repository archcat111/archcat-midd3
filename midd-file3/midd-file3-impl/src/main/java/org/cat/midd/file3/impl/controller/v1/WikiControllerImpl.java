package org.cat.midd.file3.impl.controller.v1;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.cat.app.user3.client.feign.UserLoginFeignClient;
import org.cat.app.user3.client.session.SessionUser;
import org.cat.core.web3.controller.AbsArchRestRespController;
import org.cat.core.web3.log.constants.EventLogConstants;
import org.cat.core.web3.log.event.EventLog;
import org.cat.core.web3.resp.ArchResultResp;
import org.cat.midd.file3.client.controller.WikiController;
import org.cat.midd.file3.client.exception.WikiException;
import org.cat.midd.file3.client.exception.enums.WikiExceptionEnum;
import org.cat.midd.file3.impl.config.wiki.WikiProperties;
import org.cat.midd.file3.impl.config.wiki.WikiUploadProperties;
import org.cat.midd.file3.impl.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.io.file.FileNameUtil;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.swagger.annotations.Api;

/**
 * 
 * @author 王云龙
 * @date 2022年12月15日 上午11:37:21
 * @version 1.0
 * @description Wiki文件上传与下载的http接口实现类
 * 		RestController
 * 		标识@Validated标识该类开启参数验证（需要Bean类型参数单独添加@Validated，普通类型参数在接口中标识即可）
 * 		标识@API表示该类开启Swagger
 * 		标识@EventLog标识该类开启EventLog
 * 		
 *		//参数校验
		//参数清洗
		//构建Service参数
		//调用业务逻辑
		//数据反清洗
		//返回数据
 *
 */
@EventLog(type = EventLogConstants.Type.HTTP_CONTROLLER_EXECUTE, apiName = "Wiki文件Feign")
@RestController
@Api(description = "Wiki文件处理接口")
@Validated 
public class WikiControllerImpl extends AbsArchRestRespController implements WikiController {
	
	@Autowired
	private WikiProperties wikiProperties;
	@Autowired
	private WikiService wikiService;
	@Autowired
	private UserLoginFeignClient userLoginFeignClient;

	@Override
	public ArchResultResp<String> insertDocImage(MultipartFile docImageFile) {
		//参数校验
		ArchResultResp<SessionUser> resultRespForSessionUser = userLoginFeignClient.currentLoginUser();
		SessionUser sessionUser = resultRespForSessionUser.getResponse();
		if(sessionUser==null || sessionUser.getCode()==null) {
			throw new WikiException(WikiExceptionEnum.GET_CURRENT_USER_E_NOT_LOGIN);
		}
		WikiUploadProperties wikiUploadProperties = wikiProperties.getUpload();
		//参数校验 - 开关
		if(!wikiUploadProperties.isAllowUploadWikiImage()) {
			throw new WikiException(WikiExceptionEnum.INSERT_USER_DOC_IMAGE_E_NOT_ALLOW_UPLOAD);
		}
		//参数校验 - 后缀名
		String originalFileName=docImageFile.getOriginalFilename();
		String fileSuffix = FileNameUtil.getSuffix(originalFileName);
		if(!wikiUploadProperties.getAllowWikiImageSuffixes().contains(fileSuffix)) {
			throw new WikiException(WikiExceptionEnum.INSERT_USER_DOC_IMAGE_E_FILE_SUFFIX_NOT_ALLOWED);
		}
		//参数校验 - contentType
		String contentType = docImageFile.getContentType();
		if(!wikiUploadProperties.getAllowWikiImageContentTypes().contains(contentType)) {
			throw new WikiException(WikiExceptionEnum.INSERT_USER_DOC_IMAGE_E_FILE_CONTENT_TYPE_NOT_ALLOWED);
		}
		//参数校验 - 文件大小
		DataSize docImageSize = DataSize.ofBytes(docImageFile.getSize());
		DataSize allowdocImageSize = wikiUploadProperties.getAllowWikiImageSize();
		if(allowdocImageSize.compareTo(docImageSize)==-1) { //A<B为-1，A=B为0，A>B为1
			throw new WikiException(WikiExceptionEnum.INSERT_USER_DOC_IMAGE_E_FILE_GT_ALLOW_SIZE);
		}
		//参数清洗
		//构建Service参数
		Long userCode = sessionUser.getCode();
		//调用业务逻辑
		String objectName = null; //doc/image/user-836004079814057984/892167133760929792-1660624681925.png
		try(InputStream inputStream = docImageFile.getInputStream();) {
			objectName = wikiService.insertDocImage(inputStream, contentType, originalFileName, userCode, fileSuffix);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IOException e) {
			e.printStackTrace();
			throw new WikiException(WikiExceptionEnum.INSERT_USER_DOC_IMAGE_E);
		}
		//数据反清洗
		//返回数据
		return resultRespGenerator.doResultResp(objectName);
	}

	@Override
	public void selectDocImage(Long userCode, String docImageFileName) {
		//参数校验
		
		//构建Service参数
		//调用业务逻辑
		try(InputStream inputStream = wikiService.selectDocImage(userCode, docImageFileName);) {
			super.downloadGenerator.downloadStream(inputStream, httpServletResponse);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IOException e1) {
			e1.printStackTrace();
			throw new WikiException(WikiExceptionEnum.SELECT_USER_DOC_IMAGE_E);
		}
	}

	@Override
	public ArchResultResp<String> insertNoteImage(MultipartFile noteImageFile) {
		//参数校验
		ArchResultResp<SessionUser> resultRespForSessionUser = userLoginFeignClient.currentLoginUser();
		SessionUser sessionUser = resultRespForSessionUser.getResponse();
		if(sessionUser==null || sessionUser.getCode()==null) {
			throw new WikiException(WikiExceptionEnum.GET_CURRENT_USER_E_NOT_LOGIN);
		}
		WikiUploadProperties wikiUploadProperties = wikiProperties.getUpload();
		//参数校验 - 开关
		if(!wikiUploadProperties.isAllowUploadWikiImage()) {
			throw new WikiException(WikiExceptionEnum.INSERT_USER_NOTE_IMAGE_E_NOT_ALLOW_UPLOAD);
		}
		//参数校验 - 后缀名
		String originalFileName=noteImageFile.getOriginalFilename();
		String fileSuffix = FileNameUtil.getSuffix(originalFileName);
		if(!wikiUploadProperties.getAllowWikiImageSuffixes().contains(fileSuffix)) {
			throw new WikiException(WikiExceptionEnum.INSERT_USER_NOTE_IMAGE_E_FILE_SUFFIX_NOT_ALLOWED);
		}
		//参数校验 - contentType
		String contentType = noteImageFile.getContentType();
		if(!wikiUploadProperties.getAllowWikiImageContentTypes().contains(contentType)) {
			throw new WikiException(WikiExceptionEnum.INSERT_USER_NOTE_IMAGE_E_FILE_CONTENT_TYPE_NOT_ALLOWED);
		}
		//参数校验 - 文件大小
		DataSize noteImageSize = DataSize.ofBytes(noteImageFile.getSize());
		DataSize allowNoteImageSize = wikiUploadProperties.getAllowWikiImageSize();
		if(allowNoteImageSize.compareTo(noteImageSize)==-1) { //A<B为-1，A=B为0，A>B为1
			throw new WikiException(WikiExceptionEnum.INSERT_USER_NOTE_IMAGE_E_FILE_GT_ALLOW_SIZE);
		}
		//参数清洗
		//构建Service参数
		Long userCode = sessionUser.getCode();
		//调用业务逻辑
		String objectName = null; //note/image/user-836004079814057984/892167133760929792-1660624681925.png
		try(InputStream inputStream = noteImageFile.getInputStream();) {
			objectName = wikiService.insertNoteImage(inputStream, contentType, originalFileName, userCode, fileSuffix);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IOException e) {
			e.printStackTrace();
			throw new WikiException(WikiExceptionEnum.INSERT_USER_NOTE_IMAGE_E);
		}
		//数据反清洗
		//返回数据
		return resultRespGenerator.doResultResp(objectName);
	}

	@Override
	public void selectNoteImage(Long userCode, String noteImageFileName) {
		//参数校验
		
		//构建Service参数
		//调用业务逻辑
		try(InputStream inputStream = wikiService.selectNoteImage(userCode, noteImageFileName);) {
			super.downloadGenerator.downloadStream(inputStream, httpServletResponse);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IOException e1) {
			e1.printStackTrace();
			throw new WikiException(WikiExceptionEnum.SELECT_USER_NOTE_IMAGE_E);
		}
	}

	@Override
	public ArchResultResp<String> insertQAImage(MultipartFile qaImageFile) {
		//参数校验
		ArchResultResp<SessionUser> resultRespForSessionUser = userLoginFeignClient.currentLoginUser();
		SessionUser sessionUser = resultRespForSessionUser.getResponse();
		if(sessionUser==null || sessionUser.getCode()==null) {
			throw new WikiException(WikiExceptionEnum.GET_CURRENT_USER_E_NOT_LOGIN);
		}
		WikiUploadProperties wikiUploadProperties = wikiProperties.getUpload();
		//参数校验 - 开关
		if(!wikiUploadProperties.isAllowUploadWikiImage()) {
			throw new WikiException(WikiExceptionEnum.INSERT_USER_QA_IMAGE_E_NOT_ALLOW_UPLOAD);
		}
		//参数校验 - 后缀名
		String originalFileName=qaImageFile.getOriginalFilename();
		String fileSuffix = FileNameUtil.getSuffix(originalFileName);
		if(!wikiUploadProperties.getAllowWikiImageSuffixes().contains(fileSuffix)) {
			throw new WikiException(WikiExceptionEnum.INSERT_USER_QA_IMAGE_E_FILE_SUFFIX_NOT_ALLOWED);
		}
		//参数校验 - contentType
		String contentType = qaImageFile.getContentType();
		if(!wikiUploadProperties.getAllowWikiImageContentTypes().contains(contentType)) {
			throw new WikiException(WikiExceptionEnum.INSERT_USER_QA_IMAGE_E_FILE_CONTENT_TYPE_NOT_ALLOWED);
		}
		//参数校验 - 文件大小
		DataSize qaImageSize = DataSize.ofBytes(qaImageFile.getSize());
		DataSize allowQAImageSize = wikiUploadProperties.getAllowWikiImageSize();
		if(allowQAImageSize.compareTo(qaImageSize)==-1) { //A<B为-1，A=B为0，A>B为1
			throw new WikiException(WikiExceptionEnum.INSERT_USER_QA_IMAGE_E_FILE_GT_ALLOW_SIZE);
		}
		//参数清洗
		//构建Service参数
		Long userCode = sessionUser.getCode();
		//调用业务逻辑
		String objectName = null; //qa/image/user-836004079814057984/892167133760929792-1660624681925.png
		try(InputStream inputStream = qaImageFile.getInputStream();) {
			objectName = wikiService.insertQAImage(inputStream, contentType, originalFileName, userCode, fileSuffix);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IOException e) {
			e.printStackTrace();
			throw new WikiException(WikiExceptionEnum.INSERT_USER_QA_IMAGE_E);
		}
		//数据反清洗
		//返回数据
		return resultRespGenerator.doResultResp(objectName);
	}

	@Override
	public void selectQAImage(Long userCode, String qaImageFileName) {
		//参数校验
		
		//构建Service参数
		//调用业务逻辑
		try(InputStream inputStream = wikiService.selectQAImage(userCode, qaImageFileName);) {
			super.downloadGenerator.downloadStream(inputStream, httpServletResponse);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IOException e1) {
			e1.printStackTrace();
			throw new WikiException(WikiExceptionEnum.SELECT_USER_QA_IMAGE_E);
		}
	}

}
