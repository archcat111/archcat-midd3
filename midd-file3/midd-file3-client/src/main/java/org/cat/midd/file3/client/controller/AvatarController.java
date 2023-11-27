package org.cat.midd.file3.client.controller;

import javax.validation.constraints.NotNull;

import org.cat.core.web3.resp.ArchResultResp;
import org.cat.midd.file3.client.constants.AppConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 王云龙
 * @date 2022年7月27日 下午5:46:41
 * @version 1.0
 * @description 头像上传和下载
 * 	//参数校验
 * //参数清洗
 * //构建Service参数
 * //调用业务逻辑
 * //数据反清洗
 * //返回数据
 * 
 *
 */
public interface AvatarController {
	
	@ApiOperation(value = "上传用户头像")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "SESSION", value = "SESSION ID", paramType = "header", required = true, dataType = "String")
	})
	@PostMapping(path = AppConstants.Url.AVATAR_BASE_PATH+"/users")
	public ArchResultResp<String> insertAvatar(
			@NotNull(message = "头像文件不能为空") @RequestPart("avatarFile") MultipartFile avatarFile);
	
	@ApiOperation(value = "获取头像", notes="获取用户头像")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userCode", value = "用户code", paramType = "path", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "avatarFileName", value = "头像文件名称", paramType = "query", required = true, dataType = "String"),
	})
	@GetMapping(path = AppConstants.Url.AVATAR_BASE_PATH+"/users/{userCode}")
	public void selectAvatar(
			@PathVariable("userCode") Long userCode,
			@NotNull(message = "头像文件名称不能为空") String avatarFileName);

}
