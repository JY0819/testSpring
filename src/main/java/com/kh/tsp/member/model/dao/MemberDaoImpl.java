package com.kh.tsp.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.tsp.member.model.exception.LoginException;
import com.kh.tsp.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member loginCheck(SqlSessionTemplate sqlSession, Member m) throws LoginException {
		Member loginUser = sqlSession.selectOne("Member.loginCheck", m);
		System.out.println("Dao Member > "+ loginUser);
		
		if(loginUser == null) {
			throw new LoginException("로그인 정보가 존재하지 않습니다.");
		}
		return loginUser;
		
	}

	@Override
	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("Member.insertMember", m);
	}

	// 암호화 된 비밀번호 조회용 메소드
	@Override
	public String selectEncPassword(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("Member.selectPwd", m.getUserId());
	}

	// 로그인 정보 조회용 메소드
	@Override
	public Member selectMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("Member.selectLoginUser", m);
	}

	@Override
	public int insertBoard(SqlSessionTemplate sqlSession) {
		return sqlSession.insert("Member.insertBoard");
	}

	

}
