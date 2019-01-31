package com.kh.tsp.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterThrowingAdvice {
	@Pointcut("execution(* com.kh.tsp..*ServiceImpl.*(..))")
	public void allPointcut() {}
	
	@AfterThrowing(pointcut="allPointcut()", throwing="exceptObj")
	public void exceptionLog(JoinPoint jp, Exception exceptObj) {
		String methodName = jp.getSignature().getName();	
		
		if (exceptObj instanceof IllegalArgumentException) {
			System.out.println("부적합한 값이 입력되었습니다.");
		} else if (exceptObj instanceof NumberFormatException) {
			System.out.println("숫자 형식의 값이 아닙니다.");
		} else if (exceptObj instanceof Exception) {
			System.out.println("예외가 발생했습니다.");
			System.out.println("예외명 > "+ exceptObj.getMessage());
		} else {
			System.out.println(exceptObj.getMessage());
		}
	}
}
