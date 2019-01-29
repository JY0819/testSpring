package com.kh.tsp.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Override
	public Member loginMember(Member m) throws LoginException {
		
//		System.out.println(sqlSession.hashCode());
		Member loginUser = md.loginCheck(sqlSession, m);
		
		return loginUser;
	}

	@Override
	public int insertMember(Member m) {
		int result = md.insertMember(sqlSession, m);
	
		return result;
	}

}
