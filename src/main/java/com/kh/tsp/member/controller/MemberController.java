package com.kh.tsp.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.tsp.member.model.exception.LoginException;
import com.kh.tsp.member.model.service.MemberService;
import com.kh.tsp.member.model.vo.Member;

@Controller
public class MemberController {
	//의존성 주입용 필드 선언
	@Autowired
	private MemberService ms;
	//
	/* 
	 1. 서블릿에서 했던 방식대로 HttpServletRequest, HttpServletResponse를 
	 매개변수로 선언하면 스프링 컨테이너가 이 메소드를 실행할 때 자동으로 두 객체를 인자로 주입해준다. 
	*/
	/*
	@RequestMapping("/login.me")	// 이 요청이 들어왔을 시 아래 메소드를 수행해라
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) {
		
		//System.out.println("확인");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println("ID > " + userId);
		System.out.println("PWD > " + userPwd);
		
		return "main/main";
	}
	*/
	
	/*
	 2. RequsetParam 어노테이션을 이용해서 파라미터 값 받아오기
	 -> 스프링에서는 조금 더 간단하게 파라미터를 받아올 수 있는 방법을 제공한다(@RequestParam)
	 */
	/*
	@RequestMapping(value="/login.me", method=RequestMethod.POST)
	public String loginCheck(@RequestParam("userId")String userId
							,@RequestParam String userPwd) {
		
		System.out.println("ID > " + userId);
		System.out.println("PWD > " + userPwd);
		
		return "main/main";
	}
	*/
	
	// 3. RequestParam 어노테이션은 생략해도 파라미터 값을 가져와서 매개변수에 저장할 수 있다.
	/*
	@RequestMapping(value="/login.me", method=RequestMethod.POST)
	public String loginCheck(String userId, String userPwd) {
		
		System.out.println("ID > " + userId);
		System.out.println("PWD > " + userPwd);
		
		return "main/main";
	}
	*/
	
	// 4. @ModelAttribute를 이용한 값 전달받는 방법
	/*
	@RequestMapping(value="/login.me", method=RequestMethod.POST)
	public String loginCheck(@ModelAttribute Member m) {
		
		System.out.println("member > " + m);
		
		return "main/main";
	}
	*/
	
	// 5. 위의 어노테이션을 생략하고 객체로 받는 방법
	@RequestMapping(value="/login.me", method=RequestMethod.POST)
	public String loginCheck(Member m, HttpServletRequest requset, HttpServletResponse response) {
		
		System.out.println("member > " + m);
		
		/*MemberService ms = new MemberServiceImpl();
		
		System.out.println(ms.hashCode());*/
		
		/* 
		의존성 주입을 통한 객체 생성
		1. 계층간의 의존관계를 줄인다.
		2. 싱글톤으로 객체를 생성하여 메모리를 효율적으로 사용한다.
		System.out.println(ms.hashCode());
		*/
		
		try {
			Member loginUser = ms.loginMember(m);
			requset.getSession().setAttribute("loginUser", loginUser);
			return "redirect:goMain.me";
			
		} catch (LoginException e) {
			requset.setAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
		
		//return "main/main";
	}
	@RequestMapping("goMain.me")
	public String goMain() {
		return "main/main";
	}
	
	@RequestMapping("logout.me")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "redirect:goMain.me";
	}
	
	
	
	
}
