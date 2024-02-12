package com.jwtapi.aspect;

import java.time.LocalDate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.jwtapi.dto.LogCallDTO;
import com.jwtapi.service.LogCallService;

//@Component
//@Aspect
public class LogCallAspect {

	@Autowired
	private LogCallService logCallService;

	@Pointcut("execution(* com.jwtapi.service.UserInfoService.*(..))")
	public void logSaveUserInfo() {
	}

	@After("logSaveUserInfo()")
	public void saveMethodExecution(JoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getSignature().getDeclaringTypeName();
		Object[] args = joinPoint.getArgs();

		StringBuilder parameters = new StringBuilder();
		for (Object arg : args) {
			if (arg != null) {
				if (parameters.length() > 0) {
					parameters.append(", ");
				}
				parameters.append(arg.toString());
			}
		}

		LogCallDTO logCallDTO = new LogCallDTO();
		logCallDTO.setUrl(className + "." + methodName);
		logCallDTO.setCalledDate(LocalDate.now());
		logCallDTO.setParameter(parameters.toString());

		logCallService.save(logCallDTO);
	}
}
