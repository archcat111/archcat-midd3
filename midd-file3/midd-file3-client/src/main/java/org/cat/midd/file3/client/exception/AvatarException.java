package org.cat.midd.file3.client.exception;

import org.cat.core.exception3.ParentRuntimeException;
import org.cat.core.web3.constants.ArchVersion;
import org.cat.midd.file3.client.exception.enums.AvatarExceptionEnum;

/**
 * 
 * @author 王云龙
 * @date 2022年7月28日 下午6:54:50
 * @version 1.0
 * @description 用户头像自定义服务异常
 *
 */
public class AvatarException extends ParentRuntimeException{

	private static final long serialVersionUID = ArchVersion.V3;
	
	
	public AvatarException(AvatarExceptionEnum avatarExceptionEnum) {
		super(avatarExceptionEnum.getErrorCode(), avatarExceptionEnum.getErrorMsg());
	}


}
