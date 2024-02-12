package com.jwtapi.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.jwtapi.service.LogCallService;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@ConditionalOnProperty(name = "execution.time.aspect.enabled", havingValue = "true", matchIfMissing = true)
public class ExecutionTimeAspect {

	@Autowired
	private LogCallService logCallService;

	@Value("${execution.time.aspect.date.format}")
	private String formatDate;

	@Pointcut("within(com.jwtapi.service.*)")
	public void executionTimePackage() {
	}

	@Around("executionTimePackage()")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		long startTime = System.currentTimeMillis();
		log.info("Before executing: " + joinPoint.getSignature().toShortString() + " : "
				+ sdf.format(new Date(startTime)));

		Object obj = joinPoint.proceed();

		long endTime = System.currentTimeMillis();
		log.info(
				"After executing: " + joinPoint.getSignature().toShortString() + " : " + sdf.format(new Date(endTime)));
		log.info("Method execution time " + joinPoint.getSignature().toShortString() + " : " + (endTime - startTime)
				+ " milliseconds");

		return obj;

	}
}
