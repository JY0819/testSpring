package com.kh.tsp.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.tsp.member.model.exception.LoginException;
import com.kh.tsp.member.model.vo.Member;

public interface MemberDao {

	Member loginCheck(SqlSessionTemplate sqlSession, Member m) throws LoginException;

	int insertMember(SqlSessionTemplate sqlSession, Member m);

	String selectEncPassword(SqlSessionTemplate sqlSession, Member m);

	Member selectMember(SqlSessionTemplate sqlSession, Member m);

	int insertBoard(SqlSessionTemplate sqlSession);

}
