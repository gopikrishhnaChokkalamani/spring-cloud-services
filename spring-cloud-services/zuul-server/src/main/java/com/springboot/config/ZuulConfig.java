package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.filter.AuditLogFilter;

@Configuration
public class ZuulConfig {
	
	@Bean
	public AuditLogFilter auditLogFilter() {
		return new AuditLogFilter();
	}

}
