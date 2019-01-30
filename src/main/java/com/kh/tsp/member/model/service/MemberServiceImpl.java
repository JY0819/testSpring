package com.kh.tsp.member.model.service;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.kh.tsp.member.model.dao.MemberDao;
import com.kh.tsp.member.model.exception.LoginException;
import com.kh.tsp.member.model.vo.Member;

/*@Component*/
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private MemberDao md;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	
	/*@Override
	public Member loginMember(Member m) throws LoginException {
		
		System.out.println(sqlSession.hashCode());
		Member loginUser = md.loginCheck(sqlSession, m);
		
		
		
		return loginUser;
	}*/

	
	/*@Override
	public int insertMember(Member m) {
		
		int result = md.insertMember(sqlSession, m);
		
		//트랜젝션처리 하지 않았음
		//기본적으로 스프링은 트랜젝션 처리를 자동으로 한다.
		
		
		return result;
	}*/

	//암호화 후 로그인용 메소드
	@Override
	public Member loginMember(Member m) throws LoginException {
		
		Member loginUser = null;
		
		String encPassword = md.selectEncPassword(sqlSession, m);
		
		if(!passwordEncoder.matches(m.getUserPwd(), encPassword)) {
			// 일치하지 않을 시
			throw new LoginException("로그인 실패!");
		}else {
			loginUser = md.selectMember(sqlSession, m);
		}
		
		return loginUser;
	}


	// 1. 트랜젝션 기본값 설정 후 트랜젝션 상태 관리하는 객체를 통해 트랜젝션을 처리하는 방법
	/*@Override
	public int insertMember(Member m) {
		//트랜젝션에 대해 기본 세팅을 하기 위해 필요한 객체
		DefaultTransactionDefinition def 
							= new DefaultTransactionDefinition();
		
		//하나의 트랜젝션이 존재한다면 그 이후 트랜젝션은 그 트랜젝션을 지원하고
		//트랜젝션이 없다면 새로운 트랜젝션을 시작한다는 의미이다.
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			sqlSession.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//그 이후에 비즈니스 로직을 수행한다.
		int result1 = md.insertMember(sqlSession, m);
		int result2 = md.insertBoard(sqlSession);
		
		int result = 0;
		
		if(result1 > 0 && result2 > 0) {
			transactionManager.commit(status);
			result = 1;
		}else {
			transactionManager.rollback(status);
			result = 0;
		}
		
		
		return result;
	}*/

	
	//2. @Transactional 어노테이션을 이용하는 방법
	/*@Override
	//Isolation.SERIALIZABLE = 트랜젝션의 격리 수준을 지정
	//트랜젝션이 완료되기 전까지는 다른 트랜젝션을 수정할 수 없게 하는 옵션이다.
	@Transactional(propagation=Propagation.REQUIRED, 
					isolation=Isolation.SERIALIZABLE,
					rollbackFor= {Exception.class})
	public int insertMember(Member m) {
		int result = 0;
		
		int result1 = md.insertMember(sqlSession, m);
		int result2 = md.insertBoard(sqlSession);
		
		if(result1 > 0 && result2 > 0) {
			result = 1;
		}else {
			result = 0;
		}
		
		return result;
	}*/
	
	//3. xml에 AOP 설정해서 트랜젝션 적용하는 방법
	@Override
	public int insertMember(Member m) {
/*		int result = 0;
		
		int result1 = md.insertMember(sqlSession, m);
		int result2 = md.insertBoard(sqlSession);
		
		if(result1 > 0 && result2 > 0) {
			result = 1;
		}else {
			result = 0;
		}
		
		return result;*/
		
		return md.insertMember(sqlSession, m);
	}
}
