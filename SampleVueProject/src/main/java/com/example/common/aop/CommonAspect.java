package com.example.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
// AOP
// 대상(패키지,클래스메서드) 호출전, 호출 후 처리
public class CommonAspect {
		
	@Pointcut("execution(* com.example..*(..))") // 어떤 패키지/클래스/메소드에 대해 aop를 적용할 것인지 명시
	private void doExecute() {}
	
	@Around("doExecute()")	// 해당 메소드 호출 전/후로 호출
	public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable{
		// 여기선 일단 로그 남김
		log.debug("In dologging");
		
		String methodName = joinPoint.getSignature().toShortString();
		try {
			log.debug(methodName+" is start");
			Object obj = joinPoint.proceed();
			return obj;
		}finally {
			log.debug(methodName + " is Finish");
		}
	}
}
