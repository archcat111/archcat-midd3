package org.cat.midd.file3.impl.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

/**
 * 
 * @author 王云龙
 * @date 2022年7月28日 下午6:24:38
 * @version 1.0
 * @description 头像业务接口
 *
 */
public interface AvatarService {

	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年7月28日 下午6:24:35
	 * @version 1.0
	 * @description 上传头像 
	 * @param inputStream		用户上传头像图片的输入流
	 * @param contentType		从用户上传Http请求中获取的contentType
	 * @param originalFileName		原始文件名
	 * @param userCode		用户code
	 * @param fileSuffix	需要存储的头像文件的后缀名
	 * @return 例如：完整的objectName为：user/<userCode>/111.png，这里返回111.png
	 * @throws IOException 
	 * @throws XmlParserException 
	 * @throws ServerException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidResponseException 
	 * @throws InternalException 
	 * @throws InsufficientDataException 
	 * @throws ErrorResponseException 
	 * @throws InvalidKeyException 
	 */
	public String insertAvatar(InputStream inputStream, String contentType, String originalFileName, Long userCode, String fileSuffix) 
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, 
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;

	/**
	 * 
	 * @author wangyunlong
	 * @date 2022年7月28日 下午6:50:26
	 * @version 1.0
	 * @description 从存储中找到头像文件，并返回一个inputStream 
	 * @param userCode
	 * @param avatarFileName 在存储中存着的文件名，并非完全文件名
	 * 		例如：完整文件名objectName为user/<userCode>/111.png，这个地方的avatarFileName为其中的111.png
	 * @return
	 * @throws InvalidKeyException
	 * @throws ErrorResponseException
	 * @throws InsufficientDataException
	 * @throws InternalException
	 * @throws InvalidResponseException
	 * @throws NoSuchAlgorithmException
	 * @throws ServerException
	 * @throws XmlParserException
	 * @throws IOException
	 */
	public InputStream selectAvatar(Long userCode, String avatarFileName)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;

}
