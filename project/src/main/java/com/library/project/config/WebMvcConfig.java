package com.library.project.config;

import java.io.IOException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.nimbusds.jose.util.StandardCharset;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${file.path}")
	private String filePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///"+filePath)
		.setCachePeriod(60*60)
		.resourceChain(true)
		.addResolver(new PathResourceResolver() {
			//파일명 한글 입력 
			@Override
			protected Resource getResource(String resourcePath, Resource location) throws IOException {
				resourcePath =URLDecoder.decode(resourcePath, StandardCharset.UTF_8);
				return super.getResource(resourcePath, location);
			}
		});
	}
}
