package com.library.project.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class);
	
	@Around("within(com.library.project..*)")
	public  Object loggin(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
		long startAt = System.currentTimeMillis();
		
		Map<String, Object> params = getParams(proceedingJoinPoint);
		
		LOGGER.info("-------Advice Call :{}({})={}",proceedingJoinPoint.getSignature().getDeclaringTypeName(),proceedingJoinPoint.getSignature().getName(), params);
		
		Object result =proceedingJoinPoint.proceed();
		
		long endAt = System.currentTimeMillis();
		
		LOGGER.info("-------Advice End :{}({})={}({}ms)",proceedingJoinPoint.getSignature().getDeclaringTypeName(),proceedingJoinPoint.getSignature().getName(), result, endAt-startAt);
		
		return result;
	}
	private Map<String,Object> getParams(ProceedingJoinPoint pjp){
		Map<String, Object> params = new HashMap<String, Object>();
		Object[] args = pjp.getArgs();
		String[] argNames = ((CodeSignature)pjp.getSignature()).getParameterNames(); //CodeSignature로 다운 캐스팅을 시킬 수 있음. 다운캐스팅을 시켜야 getParameterNames을 사용할 수 있다.
		for(int i =0; i<args.length;i++) {
			params.put(argNames[i],args[i]);
		}
		return params;
	}
		
	
}
