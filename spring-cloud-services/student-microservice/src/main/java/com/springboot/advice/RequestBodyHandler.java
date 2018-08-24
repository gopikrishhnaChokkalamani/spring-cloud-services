package com.springboot.advice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.springboot.exception.DuplicateStudentRecordException;
import com.springboot.model.Student;
import com.springboot.service.RequestCaptureService;

@RestControllerAdvice
public class RequestBodyHandler implements RequestBodyAdvice {

	@Autowired
	private RequestCaptureService requestCapture;

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return (methodParameter.getMethodAnnotations() != null) ? Arrays.asList(methodParameter.getMethodAnnotations())
				.stream().anyMatch(t -> t.annotationType().equals(PostMapping.class)) : false;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return body;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		// TODO Auto-generated method stub
		return inputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		if (!requestCapture.captureIncomingRequests((Student) body))
			throw new DuplicateStudentRecordException("record already present!!!");
		return body;
	}
}
