package com.kh.tsp.member.model.service;

import com.kh.tsp.member.model.exception.LoginException;
import com.kh.tsp.member.model.vo.Member;

public interface MemberService {

	Member loginMember(Member m) throws LoginException;

	int insertMember(Member m);
	

}
