package com.model2_cgv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	/**
	 * header.do
	 */
	@RequestMapping(value="/header.do", method=RequestMethod.GET)
	public String header() {
		return "header";
	}
	
	/**
	 * footer.do
	 */
	@RequestMapping(value="/footer.do", method=RequestMethod.GET)
	public String footer() {
		return "footer";
	}
	
	/**
	 * index.do
	 */
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	/**
	 * preparing.do
	 */
	@RequestMapping(value="/preparing.do", method=RequestMethod.GET)
	public String preparing() {
		return "preparing";
	}
	
	/**
	 * errorPage.do
	 */
	@RequestMapping(value="/errorPage.do", method=RequestMethod.GET)
	public String errorPage() {
		return "errorPage";
	}
}
