package org.cat.midd.file3.client.feign;

import org.cat.midd.file3.client.constants.AppConstants;
import org.cat.midd.file3.client.controller.AvatarController;
import org.springframework.cloud.openfeign.FeignClient;

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
@FeignClient(name = AppConstants.FULL_FEIGN_AVATAR, url="${app-file3-impl.feign.avatar.url:}")
public interface AvatarFeignClient extends AvatarController {
	
}
