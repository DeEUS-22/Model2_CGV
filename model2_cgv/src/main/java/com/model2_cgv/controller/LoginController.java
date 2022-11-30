package com.model2_cgv.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model2_cgv.service.MemberServiceImpl;
import com.model2_cgv.vo.CgvMemberVO;
import com.model2_cgv.vo.SessionVO;

@Controller
public class LoginController {

	@Autowired
	private MemberServiceImpl memberService;
	
	/**
	 * login.do
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public ModelAndView login(String auth) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("auth", auth);
		mv.setViewName("/login/login");
		return mv;
	}
	
	/**
	 * loginCheck.do
	 */
	@RequestMapping(value="/loginCheck.do", method=RequestMethod.POST)
	public ModelAndView loginCheck(CgvMemberVO vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		SessionVO svo = memberService.getLoginResult(vo); 
		
		if(svo != null) {
			if(svo.getLoginresult() == 1){
				//로그인 성공 --> session객체에 key(sid),value(로그인계정) 추가 후 index 페이지로 이동
				//session.setAttribute("sid", vo.getId());
				session.setAttribute("svo", svo);
				mv.addObject("login_result","ok");
				mv.setViewName("index");
			}
		}else{
			mv.addObject("login_result","fail");
			mv.setViewName("/login/login");
		}
				
		return mv;
	}
	
	/**
	 * logout.do : 로그아웃
	 */
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		//세션정보를 가져와서 sid 값이 null이 아니면 session.invalidate 메소드 호출
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		
		if(svo != null) {
			session.invalidate();	//세션 정보 삭제
			mv.addObject("logout_result","ok"); //index 페이지에서 logout_result 값을 받아서 ok인 경우 alert 창을 띄움
		}
		
		mv.setViewName("/index");
		
		return mv;
		
	}
	
}
