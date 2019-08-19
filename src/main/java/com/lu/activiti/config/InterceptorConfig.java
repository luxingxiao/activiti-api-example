package com.lu.activiti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lu.activiti.jwt.JwtInterceptor;

/**
 * 
 * @author luxingxiao
 *
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		addInterceptor(jwtInterceptor, registry);
	}

	private void addInterceptor(HandlerInterceptor interceptor, InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns("/api/**");
	}
}
