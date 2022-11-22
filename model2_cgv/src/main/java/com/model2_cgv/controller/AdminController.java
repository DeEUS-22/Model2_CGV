package com.model2_cgv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	/**
	 * admin_list.do
	 */
	@RequestMapping(value="/admin.do", method=RequestMethod.GET)
	public String admin_list() {
		return "/admin/admin";
	}
	
	/**
	 * board_list.do
	 */
	@RequestMapping(value="/admin_member_list.do", method=RequestMethod.GET)
	public String admin_member_list() {
		return "/admin/admin_member/admin_member_list";
	}
	
	/**
	 * board_list.do
	 */
	@RequestMapping(value="/admin_notice_list.do", method=RequestMethod.GET)
	public String admin_notice_list() {
		return "/admin/admin_notice/admin_notice_list";
	}
}
