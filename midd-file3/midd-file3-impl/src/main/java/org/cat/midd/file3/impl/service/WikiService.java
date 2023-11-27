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

public interface WikiService {

	
	public String insertDocImage(InputStream inputStream, String contentType, String originalDocImageName, Long userCode, String fileSuffix) 
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, 
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;
	
	public InputStream selectDocImage(Long userCode, String docImageName)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;
	
	public String insertNoteImage(InputStream inputStream, String contentType, String originalNoteImageName, Long userCode, String fileSuffix) 
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, 
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;
	
	public InputStream selectNoteImage(Long userCode, String noteImageName)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;
	
	public String insertQAImage(InputStream inputStream, String contentType, String originalQAImageName, Long userCode, String fileSuffix) 
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, 
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;
	
	public InputStream selectQAImage(Long userCode, String qaImageName)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;
}
