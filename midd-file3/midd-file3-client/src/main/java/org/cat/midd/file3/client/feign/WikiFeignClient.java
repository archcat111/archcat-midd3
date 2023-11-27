package org.cat.midd.file3.client.feign;

import org.cat.midd.file3.client.constants.AppConstants;
import org.cat.midd.file3.client.controller.WikiController;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 
 * @author 王云龙
 * @date 2022年12月15日 上午11:34:54
 * @version 1.0
 * @description Wiki文件的上传与下载
 *
 */
@FeignClient(name = AppConstants.FULL_FEIGN_WIKI, url="${app-file3-impl.feign.wiki.url:}")
public interface WikiFeignClient extends WikiController {
	
}
