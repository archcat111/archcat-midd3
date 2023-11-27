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
import org.cat.midd.file3.client.controller.AvatarController;
import org.cat.midd.file3.client.exception.AvatarException;
import org.cat.midd.file3.client.exception.enums.AvatarExceptionEnum;
import org.cat.midd.file3.impl.config.avatar.AvatarProperties;
import org.cat.midd.file3.impl.config.avatar.AvatarUploadProperties;
import org.cat.midd.file3.impl.service.AvatarService;
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

@EventLog(type = EventLogConstants.Type.HTTP_CONTROLLER_EXECUTE, apiName = "用户头像Feign")
@RestController
@Api(description = "头像文件处理接口")
@Validated 
public class AvatarControllerImpl extends AbsArchRestRespController implements AvatarController {

	@Autowired
	private AvatarProperties avatarProperties;
	
	@Autowired
	private AvatarService avatarService;
	
	@Autowired
	private UserLoginFeignClient userLoginFeignClient;

	
	@Override
	public ArchResultResp<String> insertAvatar(MultipartFile avatarFile) {
		//参数校验
		ArchResultResp<SessionUser> resultRespForSessionUser = userLoginFeignClient.currentLoginUser();
		SessionUser sessionUser = resultRespForSessionUser.getResponse();
		if(sessionUser==null || sessionUser.getCode()==null) {
			throw new AvatarException(AvatarExceptionEnum.GET_CURRENT_USER_E_NOT_LOGIN);
		}

		AvatarUploadProperties avatarUploadProperties = avatarProperties.getUpload();
		//参数校验 - 开关
		if(!avatarUploadProperties.isAllowUploadAvatar()) {
			throw new AvatarException(AvatarExceptionEnum.INSERT_USER_AVATAR_E_NOT_ALLOW_UPLOAD);
		}
		//参数校验 - 后缀名
		String originalFileName=avatarFile.getOriginalFilename();
		String fileSuffix = FileNameUtil.getSuffix(originalFileName);
		if(!avatarUploadProperties.getAllowAvatarFileSuffixes().contains(fileSuffix)) {
			throw new AvatarException(AvatarExceptionEnum.INSERT_USER_AVATAR_E_FILE_SUFFIX_NOT_ALLOWED);
		}
		//参数校验 - contentType
		String contentType = avatarFile.getContentType();
		if(!avatarUploadProperties.getAllowAvatarFileContentTypes().contains(contentType)) {
			throw new AvatarException(AvatarExceptionEnum.INSERT_USER_AVATAR_E_FILE_CONTENT_TYPE_NOT_ALLOWED);
		}
		//参数校验 - 文件大小
		DataSize avatarFileSize = DataSize.ofBytes(avatarFile.getSize());
		DataSize allowAvatarFileSize = avatarUploadProperties.getAllowAvatarFileSize();
		if(allowAvatarFileSize.compareTo(avatarFileSize)==-1) { //A<B为-1，A=B为0，A>B为1
			throw new AvatarException(AvatarExceptionEnum.INSERT_USER_AVATAR_E_FILE_GT_ALLOW_SIZE);
		}
		
		//参数清洗
		//构建Service参数
		Long userCode = sessionUser.getCode();
		//调用业务逻辑
		String objectName = null; //user-836004079814057984/892167133760929792-1660624681925.png
		try(InputStream inputStream = avatarFile.getInputStream();) {
			objectName = avatarService.insertAvatar(inputStream, contentType, originalFileName, userCode, fileSuffix);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IOException e) {
			e.printStackTrace();
			throw new AvatarException(AvatarExceptionEnum.INSERT_USER_AVATAR_E);
		}
		
		//数据反清洗
		
		//返回数据
		return resultRespGenerator.doResultResp(objectName);
	}

	@Override
	public void selectAvatar(Long userCode, String avatarFileName) {
		//参数校验
		
		//构建Service参数
		//调用业务逻辑
		try(InputStream inputStream = avatarService.selectAvatar(userCode, avatarFileName);) {
			super.downloadGenerator.downloadStream(inputStream, httpServletResponse);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IOException e1) {
			e1.printStackTrace();
			throw new AvatarException(AvatarExceptionEnum.SELECT_USER_AVATAR_E);
		}
		
	}
	
}
