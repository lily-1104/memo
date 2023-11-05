package com.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.common.FileManagerService;

@Configuration		// 설정을 위한 spring bean
public class WebMvcConfig implements WebMvcConfigurer {
	
	
	// 웹 이미지 path와 서버에 업로드 된 이미지와 매핑 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
		.addResourceHandler("/images/**") 	// web의 image path  => http://localhost/images/aaaaa_1698923969256/짱아코.jpg  
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);	// 실제 파일 위치
							// "file:///" => 윈도우는 슬래시 3개, 맥은 슬래시 2개
		
	}
	
	
}
