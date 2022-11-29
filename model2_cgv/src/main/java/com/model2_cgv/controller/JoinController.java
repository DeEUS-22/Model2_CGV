package com.model2_cgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model2_cgv.service.MemberServiceImpl;
import com.model2_cgv.vo.CgvMemberVO;

@Controller
public class JoinController {

	@Autowired
	private MemberServiceImpl memberService;
	
	/**
	 * join.do
	 */
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String login() {
		return "/join/join";
	}
	
	/**
	 * joinCheck.do : ȸ������ ó��
	 */
	@RequestMapping(value="/joinCheck.do", method=RequestMethod.POST)
	public ModelAndView joinCheck(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		int result = memberService.getJoinResult(vo);
		
		if(result == 1){
			mv.addObject("join_result","ok");
			mv.setViewName("/login/login");
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}
}
