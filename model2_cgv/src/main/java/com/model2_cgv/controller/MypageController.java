package com.model2_cgv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MypageController {
	
	/**
	 * mycgv.do
	 */
	@RequestMapping(value="/mycgv.do", method=RequestMethod.GET)
	public String login() {
		return "/mycgv/mycgv";
	}
}
