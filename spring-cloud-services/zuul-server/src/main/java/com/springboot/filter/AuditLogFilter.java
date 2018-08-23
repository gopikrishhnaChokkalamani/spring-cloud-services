package com.springboot.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.springboot.intfc.AuditLoggingFeignClient;
import com.springboot.model.AuditLog;

@Component
public class AuditLogFilter extends ZuulFilter {

	@Autowired
	private AuditLoggingFeignClient feignClient;

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object run() {
		try {
			RequestContext ctx = RequestContext.getCurrentContext();
			HttpServletRequest httpServletRequest = ctx.getRequest();
			String request = StreamUtils.copyToString(httpServletRequest.getInputStream(), Charset.forName("UTF-8"));
			String response = StreamUtils.copyToString(ctx.getResponseDataStream(), Charset.forName("UTF-8"));
			String timeStamp = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());
			AuditLog log = new AuditLog();
			log.setRequest(request);
			log.setResponse(response);
			log.setEndDate(timeStamp);
			ctx.setResponseBody(response);
			log.setStartDate(ctx.get("START_TIMESTAMP") != null ? ctx.get("START_TIMESTAMP").toString() : timeStamp);
			feignClient.auditLogRequest(log);
		} catch (IOException e) {

		}
		return null;
	}
}
