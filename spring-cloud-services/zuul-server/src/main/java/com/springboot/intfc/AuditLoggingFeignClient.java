package com.springboot.intfc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.model.AuditLog;

@FeignClient(name = "audit-log-service")
public interface AuditLoggingFeignClient {

	@RequestMapping(value = "/auditLog", method = RequestMethod.POST)
	public void auditLogRequest(AuditLog auditLog);
}