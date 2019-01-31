package com.kh.tsp.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.kh.tsp.member.model.vo.Member;

@Component
@Aspect
public class AfterReturningAdvice {
	@Pointcut("execution(* com.kh.tsp..*ServiceImpl.*(..))")
	public void allPointcut() {}
	
	@AfterReturning(pointcut="allPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {	// 매개변수로 받는 [returnObj]는 returning과 동일해야 한다.
		// 비즈니스 메소드가 리턴한 결과 데이터를 다른 용도로 처리할 때 사용한다.
		String methodName = jp.getSignature().getName();	
		
		if (returnObj instanceof Member) {
			Member m = (Member) returnObj;
			if (m.getUserId().equals("user03")) {
				System.out.println("로그인 : user03");
			}
		}
		System.out.println("[메소드 리턴]" + methodName + "() 메소드 리턴값 : " + returnObj.toString());
	}
	// 정상적으로 작동해야만 수행하는 메소드
}
