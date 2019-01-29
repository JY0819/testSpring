package com.kh.tsp.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tsp.member.model.exception.LoginException;
import com.kh.tsp.member.model.service.MemberService;
import com.kh.tsp.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller
public class MemberController {
	//의존성 주입용 필드 선언
	@Autowired
	private MemberService ms;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
	
/*	
	// 5. 위의 어노테이션을 생략하고 객체로 받는 방법
	@RequestMapping(value="/login.me", method=RequestMethod.POST)
	public String loginCheck(Member m, HttpServletRequest requset, HttpServletResponse response) {
		
		System.out.println("member > " + m);
		
		MemberService ms = new MemberServiceImpl();
		
		System.out.println(ms.hashCode());
		
		 
		의존성 주입을 통한 객체 생성
		1. 계층간의 의존관계를 줄인다.
		2. 싱글톤으로 객체를 생성하여 메모리를 효율적으로 사용한다.
		System.out.println(ms.hashCode());
		
		
		
		try {
			Member loginUser = ms.loginMember(m);
			requset.getSession().setAttribute("loginUser", loginUser);
			return "redirect:goMain.me";
			
		} catch (LoginException e) {
			requset.setAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
		
		
		//return "main/main";
	}*/
	
	@RequestMapping("goMain.me")
	public String goMain() {
		return "main/main";
	}
	
	/*
	@RequestMapping("logout.me")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "redirect:goMain.me";
	}
	*/
	
	
	/*
	 6. ModelAndView로 리턴 처리
	 model은 뷰로 전달한 데이터를 맵 형식으로 담을 때 객체로 scope는 request이다.
	 view는 requestDispatcher처럼 forward할 뷰 페이지 정보를 담은 객체이다.
	 ModelAndView는 이 두가지를 합쳐놓은 객체이며 
	 Model 객체를 따로 사용하는 것도 가능하지만 view 객체는 따로 사용하지 못한다.
	 */
	/*
	@RequestMapping("login.me")
	public ModelAndView loginCheck(Member m, ModelAndView mv, HttpSession session) {
		
		try {
			Member loginUser = ms.loginMember(m);
			
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:goMain.me");
			
		} catch (LoginException e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}*/
	/*
	// 7. Model 객체를 따로 사용하고 String으로 뷰 이름을 리턴하는 방법
	@RequestMapping("login.me")
	public String loginCheck(Member m, Model model, HttpSession session) {
		
		try {
			Member loginUser = ms.loginMember(m);
			
			session.setAttribute("loginUser", loginUser);
			return "redirect:goMain.me";
			
		} catch (LoginException e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	*/
	
	/* 
	8. session에 저장을 할 때 @SessionAttributes 사용하기
	모델에 Attribute가 추가될 때 자동으로 키 값을 찾아 세션에 등록하는 기능을 제공하는 어노테이션이다.
	*/
	/*@RequestMapping("login.me")
	public String loginCheck(Member m, Model model) {
		
		try {
			Member loginUser = ms.loginMember(m);
			model.addAttribute("loginUser", loginUser);
			return "redirect:goMain.me";
			
		} catch (LoginException e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}*/
	
	@RequestMapping("logout.me")
	public String logout(SessionStatus status) {
		// 세션의 상태를 확정지어주는 메소드
		status.setComplete();
		
		return "redirect:goMain.me";
	}
	
	@RequestMapping("memberJoinView.me")
	public String showMemberJoinView() {
		return "member/memberJoin";
	}
	
	@RequestMapping("insert.me")
	public String insertMember(Member m, Model model) {
		
		System.out.println("member > " + m);
		
		/*
		bcrypt란?
		DB에 비밀번호를 저장할 목적으로 설계되었다.
		
		단방향 해쉬 함수는 암호화 된 메세지를 수학적인 연산을 통해 암호화 된 메세지인 다이제스트를 생성한다.
		원본 메세지를 가지고 암호화 된 메세지를 복호화 할 수 있는 것을 양방향이라고 하고
		암호화 된 메세지를 복호화 할 수 없는 것을 단방향이라고 한다.
		
		단방향 해쉬 함수도 사용하면 안되는 이유
		1. 단방향 해쉬함수는 많은 다이제스트가 확보되면 평문을 찾아낼 수 있다.
		2. 비밀번호를 저장하기 위한 목적으로 설계된 알고리즘이 아닌, 검색을 위해 설계된 알고리즘이다.
		
		-> 이를 해결하기 위해 슬링(salting) 기법이 추가되었다.
		원본 메세지에 문자열을 추가하여 동일한 길이의 다이제스트를 생성하는 것을 슬링이라 한다.
		
		하지만 salt 값을 알아내면 나머지는 단방향 해쉬함수를 통한 다이제스트를 복호화 하는 것과 별 차이 없다.
		
		bcrypt 방식은 이러한 salt 값을 랜덤하게 생성하여 암호화를 하는 방식이다.
		추가적으로 다이제스트를 생성하는데 걸릴 시간을 결정할 수도 있다. (기본적으로 hash 보다 느리다)
		
		1999년에 발표되어 현재까지 사용되는 가장 강력한 비밀번호 저장용 매커니즘이다.
		 */
		
		String encPassword = passwordEncoder.encode(m.getUserPwd());
		
		System.out.println("변경 후 암호 > " + encPassword);
		
		m.setUserPwd(encPassword);
		
		if (m.getGender().equals("1") || m.getGender().equals("3")) {
			m.setGender("M");
		} else {
			m.setGender("F");
		}
		
		int result = ms.insertMember(m);
		
		if (result > 0) {
			return "redirect:goMain.me";
		} else {
			model.addAttribute("msg", "회원가입 실패 8ㅁ8");
			return "common/errorPage";
		}
		
//		return null;
	}
	
	@RequestMapping("login.me")
	public String loginCheck(Member m, Model model) {
		try {
			Member loginUser = ms.loginMember(m);
			
			model.addAttribute("loginUser", loginUser);
			
			return "redirect:goMain.me";
			
		} catch (LoginException e) {
			model.addAttribute("msg", e.getMessage());
			
			return "common/errorPage";
		}
		
	}
	
}
