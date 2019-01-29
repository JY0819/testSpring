package com.kh.tsp.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.tsp.member.model.dao.MemberDao;
import com.kh.tsp.member.model.exception.LoginException;
import com.kh.tsp.member.model.vo.Member;

//@Component
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private MemberDao md;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
/*	@Override
	public Member loginMember(Member m) throws LoginException {
		
//		System.out.println(sqlSession.hashCode());
		Member loginUser = md.loginCheck(sqlSession, m);
		
		return loginUser;
	}
*/
	@Override
	public int insertMember(Member m) {
		int result = md.insertMember(sqlSession, m);
	
		return result;
	}

	// 암호화 후 로그인용 메소드
	@Override
	public Member loginMember(Member m) throws LoginException {
		Member loginUser = null;
		
		String encPassword = md.selectEncPassword(sqlSession, m);
		
		if (!passwordEncoder.matches(m.getUserPwd(), encPassword)) {
			// 일치하지 않으면
			throw new LoginException("로그인 실패!");
		} else {
			loginUser = md.selectMember(sqlSession, m);
		}
		
		return loginUser;
	}

}
