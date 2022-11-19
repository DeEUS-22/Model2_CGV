package com.model2_cgv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	/**
	 * board_list.do
	 */
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public String board_list() {
		return "/board/board_list";
	}
	
	/**
	 * board_write.do
	 */
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}
	
	/**
	 * board_content.do
	 */
	@RequestMapping(value="/board_content.do", method=RequestMethod.GET)
	public String board_content() {
		return "/board/board_content";
	}
	
	/**
	 * board_update.do
	 */
	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
	public String board_update() {
		return "/board/board_update";
	}
	
	/**
	 * board_content.do
	 */
	@RequestMapping(value="/board_delete.do", method=RequestMethod.GET)
	public String board_delete() {
		return "/board/board_delete";
	}
}
