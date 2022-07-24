package com.library.project.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.library.project.handler.ex.CustomValidationApiException;

@Aspect
@Component
public class ValidationAdvice {
	private final Logger LOGGER = LoggerFactory.getLogger(ValidationAdvice.class);
	
	
	@Around("execution(* com.library.project.web.controller.api.*Controller.*(..))")
	public Object apiAdivce(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				if(bindingResult.hasErrors()) {
					Map<String, Object> errorMap = new HashMap<String, Object>();
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(),error.getDefaultMessage());
					}
					LOGGER.error("Validation aop 실행");
					throw new CustomValidationApiException("유효성 검사 실패");
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}

}
