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
 * @date 2022年12月15日 上午11:27:06
 * @version 1.0
 * @description 文档
 * 		Mapping：URL、Method
 * 		参数：RequestBody、PathVariable
 * 		验证：验证注解（其中@Validated在实现类中写）
 * 		Swagger
 */
public interface WikiController {
	
	@ApiOperation(value = "上传用户文档图片")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "SESSION", value = "SESSION ID", paramType = "header", required = true, dataType = "String")
	})
	@PostMapping(path = AppConstants.Url.WIKI_BASE_PATH+"/docs/images/users")
	public ArchResultResp<String> insertDocImage(
			@NotNull(message = "用户文档图片文件不能为空") @RequestPart("docImageFile") MultipartFile docImageFile);
	
	@ApiOperation(value = "获取用户文档图片")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userCode", value = "用户code", paramType = "path", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "docImageFileName", value = "用户文档图片文件名称", paramType = "query", required = true, dataType = "String"),
	})
	@GetMapping(path = AppConstants.Url.WIKI_BASE_PATH+"/docs/images/users/{userCode}")
	public void selectDocImage(
			@PathVariable("userCode") Long userCode,
			@NotNull(message = "用户文档图片文件名称不能为空") String docImageFileName);
	
	
	@ApiOperation(value = "上传用户笔记图片")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "SESSION", value = "SESSION ID", paramType = "header", required = true, dataType = "String")
	})
	@PostMapping(path = AppConstants.Url.WIKI_BASE_PATH+"/notes/images/users")
	public ArchResultResp<String> insertNoteImage(
			@NotNull(message = "用户笔记图片文件不能为空") @RequestPart("noteImageFile") MultipartFile noteImageFile);
	
	@ApiOperation(value = "获取用户笔记图片")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userCode", value = "用户code", paramType = "path", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "noteImageFileName", value = "用户笔记图片文件名称", paramType = "query", required = true, dataType = "String"),
	})
	@GetMapping(path = AppConstants.Url.WIKI_BASE_PATH+"/notes/images/users/{userCode}")
	public void selectNoteImage(
			@PathVariable("userCode") Long userCode,
			@NotNull(message = "用户笔记图片文件名称不能为空") String noteImageFileName);
	
	
	@ApiOperation(value = "上传用户问答图片")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "SESSION", value = "SESSION ID", paramType = "header", required = true, dataType = "String")
	})
	@PostMapping(path = AppConstants.Url.WIKI_BASE_PATH+"/qas/images/users")
	public ArchResultResp<String> insertQAImage(
			@NotNull(message = "用户问答图片文件不能为空") @RequestPart("docImageFile") MultipartFile qaImageFile);
	
	@ApiOperation(value = "获取用户问答图片")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userCode", value = "用户code", paramType = "path", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "qaImageFileName", value = "用户问答图片文件名称", paramType = "query", required = true, dataType = "String"),
	})
	@GetMapping(path = AppConstants.Url.WIKI_BASE_PATH+"/qas/images/users/{userCode}")
	public void selectQAImage(
			@PathVariable("userCode") Long userCode,
			@NotNull(message = "用户问答图片文件名称不能为空") String qaImageFileName);

}
