package org.cat.midd.file3.client.exception.enums;

public enum WikiExceptionEnum {
	
	GET_CURRENT_USER_E_NOT_LOGIN("00001","获取当前登录用户信息失败，该用户未登录"),
	
	INSERT_USER_DOC_IMAGE_E_NOT_ALLOW_UPLOAD("01001","目前禁止上传文档图片"),
	INSERT_USER_DOC_IMAGE_E_FILE_SUFFIX_NOT_ALLOWED("01002","上传文档图片时文件后缀名不被允许"),
	INSERT_USER_DOC_IMAGE_E_FILE_CONTENT_TYPE_NOT_ALLOWED("01003","上传文档图片时文件ContentType不被允许"),
	INSERT_USER_DOC_IMAGE_E_FILE_GT_ALLOW_SIZE("01004","上传文档图片时文件大于允许的图片文件大小"),
	INSERT_USER_DOC_IMAGE_E("01005","上传文档图片时发生服务端异常"),
	
	SELECT_USER_DOC_IMAGE_E("01100","获取文档图片时发生服务端异常"),
	SELECT_USER_DOC_IMAGE_E_STATUS_IS_INVALID("01101","获取文档图片时发现当前用户头像文件状态为不可用"),
	
	INSERT_USER_NOTE_IMAGE_E_NOT_ALLOW_UPLOAD("02001","目前禁止上传笔记图片"),
	INSERT_USER_NOTE_IMAGE_E_FILE_SUFFIX_NOT_ALLOWED("02002","上传笔记图片时文件后缀名不被允许"),
	INSERT_USER_NOTE_IMAGE_E_FILE_CONTENT_TYPE_NOT_ALLOWED("02003","上传笔记图片时文件ContentType不被允许"),
	INSERT_USER_NOTE_IMAGE_E_FILE_GT_ALLOW_SIZE("02004","上传笔记图片时文件大于允许的图片文件大小"),
	INSERT_USER_NOTE_IMAGE_E("02005","上传笔记图片时发生服务端异常"),
	
	SELECT_USER_NOTE_IMAGE_E("02100","获取笔记图片时发生服务端异常"),
	SELECT_USER_NOTE_IMAGE_E_STATUS_IS_INVALID("02101","获取笔记图片时发现当前用户头像文件状态为不可用"),
	
	INSERT_USER_QA_IMAGE_E_NOT_ALLOW_UPLOAD("03001","目前禁止上传问答图片"),
	INSERT_USER_QA_IMAGE_E_FILE_SUFFIX_NOT_ALLOWED("03002","上传问答图片时文件后缀名不被允许"),
	INSERT_USER_QA_IMAGE_E_FILE_CONTENT_TYPE_NOT_ALLOWED("03003","上传问答图片时文件ContentType不被允许"),
	INSERT_USER_QA_IMAGE_E_FILE_GT_ALLOW_SIZE("03004","上传问答图片时文件大于允许的图片文件大小"),
	INSERT_USER_QA_IMAGE_E("03005","上传问答图片时发生服务端异常"),
	
	SELECT_USER_QA_IMAGE_E("03100","获取问答图片时发生服务端异常"),
	SELECT_USER_QA_IMAGE_E_STATUS_IS_INVALID("03101","获取问答图片时发现当前用户头像文件状态为不可用"),
	
	;
	
	private final String prefix="1100-";
	private final String errorCode;
	private final String errorMsg;
	
	
	private WikiExceptionEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorCode() {
		return prefix+errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

//	public static WikiExceptionEnum valueOf(int errorCode) {  
//        for (WikiExceptionEnum userExceptionEnum : WikiExceptionEnum.values()) {  
//            if (userExceptionEnum.errorCode.equals(errorCode)) {  
//                return userExceptionEnum;
//            }  
//        }  
//        throw new IllegalArgumentException("No matching constant for [" + errorCode + "]");
//    }  	
	
	@Override
	public String toString() {
		return this.errorCode;
	}
}
