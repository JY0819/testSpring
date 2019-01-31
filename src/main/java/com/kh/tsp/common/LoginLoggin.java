package com.kh.tsp.common;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kh.tsp.member.model.vo.Member;

@Component
@Aspect
public class LoginLoggin {
	private static final Logger logger = LoggerFactory.getLogger(LoginLoggin.class);
	// getLogger : logger를 리턴
	
	@Pointcut("execution(* com.kh.tsp..*ServiceImpl.login*(..))")
	public void loginPointcut() {}
	
	@AfterReturning(pointcut="loginPointcut()", returning="returnObj")
	public void loginLog(JoinPoint jp, Object returnObj) {
		System.out.println("login ReturnObject : " + returnObj);
		
		if(returnObj instanceof Member) {
			Member m = (Member) returnObj;
			logger.info(new Date() + " : $" + m.getUserId() +"$ 님이 접속했습니다.");
		}
	}
	
}
