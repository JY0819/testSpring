package com.kh.tsp.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AroundAdvice {
	@Pointcut("execution(* com.kh.tsp..*ServiceImpl.*(..))")
	public void allPointcut() {}
	
	@Around("allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pj) throws Throwable {
		// 사전, 사후 처리를 모두 해결하려고 할 때 사용하는 어드바이스
		/* 후손이기 때문에 부모의 메소드 사용 가능
		   ProceedingJoinPoint = jointpoint의 후손 (더 많은 역할 수행) */
		String methodName = pj.getSignature().getName();	
		
		// 사전에 대한 처리할 내용
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// 다시 원래 흐름대로 진행
		Object obj = pj.proceed();
		
		// 사후에 대한 처리할 내용
		stopWatch.stop();
		System.out.println(methodName + "메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");
		
		return obj;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
