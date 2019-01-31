package com.kh.tsp.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeAdvice {
	
	@Pointcut("execution(* com.kh.tsp..*ServiceImpl.*(..))")
	public void allPointcut() {}
	// allPointcut = Pointcut의 이름
	
	@Before("allPointcut()")
	public void beforLog(JoinPoint jp) {
		// joinPoint를 파라미터로 받을 시 반드시 첫번째 파라미터로 받아야 한다.
		// 호출되는 대상 객체, 메소드, 전달 파라미터 목록에 접근할 수 있는 메소드를 제공해준다.
		
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		System.out.println("[메소드 호출 전 확인] > " + methodName + "() 메소드 매개변수 갯수 > " + args.length);
		
		for (int i = 0; i < args.length; i++) {
			System.out.println(i + "번째 매개변수 정보 : " + args[i].toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
