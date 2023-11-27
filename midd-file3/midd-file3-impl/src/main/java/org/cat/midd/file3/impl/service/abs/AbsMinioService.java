package org.cat.midd.file3.impl.service.abs;

import org.cat.support.storage3.generator.minio.MinioClientHolder;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

/**
 * @author 王云龙
 * @date 2022年7月28日 下午6:18:43
 * @version 1.0
 * @description 集成了一个非必须注入的MinioClientHolder
 * 		主要目的是让springbootStarter中的动态注入的MinioClient可以注入到Service的实现中
 *
 */
@Getter
public abstract class AbsMinioService {

	@Autowired(required = false)
	private MinioClientHolder minioClientHolder;
}
