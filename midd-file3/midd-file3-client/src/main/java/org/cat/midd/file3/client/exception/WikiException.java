package org.cat.midd.file3.client.exception;

import org.cat.core.exception3.ParentRuntimeException;
import org.cat.core.web3.constants.ArchVersion;
import org.cat.midd.file3.client.exception.enums.WikiExceptionEnum;

/**
 * 
 * @author 王云龙
 * @date 2022年12月15日 上午11:23:22
 * @version 1.0
 * @description Wiki文件相关自定义服务异常
 *
 */
public class WikiException extends ParentRuntimeException{

	private static final long serialVersionUID = ArchVersion.V3;
	
	
	public WikiException(WikiExceptionEnum wikiExceptionEnum) {
		super(wikiExceptionEnum.getErrorCode(), wikiExceptionEnum.getErrorMsg());
	}


}
