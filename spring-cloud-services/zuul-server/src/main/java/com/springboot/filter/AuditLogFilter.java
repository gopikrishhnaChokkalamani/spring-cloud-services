package com.springboot.filter;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.springboot.intfc.AuditLoggingFeignClient;

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
	public Object run() {
		try {
			RequestContext ctx = RequestContext.getCurrentContext();
			HttpServletRequest httpServletRequest = ctx.getRequest();
			String request = StreamUtils.copyToString(httpServletRequest.getInputStream(), Charset.forName("UTF-8"));
			String response = StreamUtils.copyToString(ctx.getResponseDataStream(), Charset.forName("UTF-8"));
			//feignClient.auditLogRequest(auditLog);
			System.out.println(request);
		} catch (IOException e) {

		}
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.POST_TYPE;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
	}

}
