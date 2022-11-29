package com.model2_cgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model2_cgv.service.MemberServiceImpl;
import com.model2_cgv.vo.CgvMemberVO;

@Controller
public class LoginController {

	@Autowired
	private MemberServiceImpl memberService;
	
	/**
	 * login.do
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "/login/login";
	}
	
	/**
	 * loginCheck.do
	 */
	@RequestMapping(value="/loginCheck.do", method=RequestMethod.POST)
	public ModelAndView loginCheck(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		int result = memberService.getLoginResult(vo); 
		
		if(result == 1) {
			mv.addObject("login_result","ok");
			mv.setViewName("index");
		}else{
			mv.addObject("login_result","fail");
			mv.setViewName("/login/login");
		}
		
		return mv;
	}
	
	
	
}
