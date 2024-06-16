package com.example.airbnb.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogingAspect {

	private static final Logger log = LogManager.getLogger(LogingAspect.class);

	@Before("execution(* com.example.airbnb.member.controller..*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("##{} Method Start ##", joinPoint.getSignature().getName());

	}

	@After("\"execution(* com.example.airbnb.member.controller..*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		log.info("##{} Method End ##", joinPoint.getSignature().getName());
	}

}
