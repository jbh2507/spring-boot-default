package com.selab.boot.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.selab.boot.filter.JSONWebTokenAuthenticationFilter;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<Filter> getFilterRegistrationBean(){
		
		FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<>(new JSONWebTokenAuthenticationFilter());
		filterRegistration.addUrlPatterns("/jwtApi/*");
		
		return filterRegistration;
	}
}
