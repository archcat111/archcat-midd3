package org.cat.midd.file3.client.exception.enums;

public enum AvatarExceptionEnum {
	
	GET_CURRENT_USER_E_NOT_LOGIN("00001","获取当前登录用户信息失败，该用户未登录"),
	
	INSERT_USER_AVATAR_E_NOT_ALLOW_UPLOAD("01001","目前禁止上传头像图片"),
	INSERT_USER_AVATAR_E("01002","上传用户头像时发生服务端异常"),
	INSERT_USER_AVATAR_E_FILE_SUFFIX_NOT_ALLOWED("01003","上传用户头像时文件后缀名不被允许"),
	INSERT_USER_AVATAR_E_FILE_CONTENT_TYPE_NOT_ALLOWED("01004","上传用户头像时文件ContentType不被允许"),
	INSERT_USER_AVATAR_E_FILE_GT_ALLOW_SIZE("01005","上传用户头像时文件大于允许的头像文件大小"),
	
	SELECT_USER_AVATAR_E("01100","获取用户头像时发生服务端异常"),
	SELECT_USER_AVATAR_E_STATUS_IS_INVALID("01101","获取用户头像时发现当前用户头像文件状态为不可用"),
	
	;
	
	private final String prefix="1000-";
	private final String errorCode;
	private final String errorMsg;
	
	
	private AvatarExceptionEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorCode() {
		return prefix+errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

//	public static AvatarExceptionEnum valueOf(int errorCode) {  
//        for (AvatarExceptionEnum userExceptionEnum : AvatarExceptionEnum.values()) {  
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
