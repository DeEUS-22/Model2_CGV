package com.model2_cgv.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model2_cgv.dao.CgvBoardDAO;
import com.model2_cgv.dao.CgvMemberDAO;
import com.model2_cgv.dao.CgvNoticeDAO;
import com.model2_cgv.vo.CgvBoardVO;
import com.model2_cgv.vo.CgvMemberVO;
import com.model2_cgv.vo.CgvNoticeVO;

@Controller
public class AdminController {
	/**
	 * admin.do
	 */
	@RequestMapping(value="/admin.do", method=RequestMethod.GET)
	public String admin_list() {
		return "/admin/admin";
	}
	
	/**
	 * admin_notice_list.do
	 */
//	@RequestMapping(value="/admin_notice_list.do", method=RequestMethod.GET)
//	public String admin_notice_list() {
//		return "/admin/admin_notice/admin_notice_list";
//	}
	
	@RequestMapping(value="/admin_notice_list.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_list() {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();

		ArrayList<CgvNoticeVO> list = dao.select();
		
		mv.addObject("list",list);
		mv.setViewName("/admin/admin_notice/admin_notice_list");
		
		return mv;
	}
	
	/**
	 * admin_notice_write.do
	 */
	@RequestMapping(value="/admin_notice_write.do", method=RequestMethod.GET)
	public String admin_notice_write() {
		return "/admin/admin_notice/admin_notice_write";
	}
	
	/**
	 * admin_notice_write_check.do : 공지사항 글쓰기 처리
	 */
	@RequestMapping(value="/adminNoticeWriteCheck.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_write_check(CgvNoticeVO vo) {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.insert(vo);
		if(result == 1){			
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{

			mv.setViewName("errorPage");
		}
		return mv;
	}
	
	/**
	 * admin_notice_content.do
	 */
//	@RequestMapping(value="/admin_notice_content.do", method=RequestMethod.GET)
//	public String admin_notice_content() {
//		return "/admin/admin_notice/admin_notice_content";
//	}
	
	@RequestMapping(value="/admin_notice_content.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid) {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		if(vo != null){
			dao.updateHits(nid);
		}
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_content");
		
		return mv;
	}
	
	/**
	 * admin_notice_update.do
	 */
//	@RequestMapping(value="/admin_notice_update.do", method=RequestMethod.GET)
//	public String admin_notice_update() {
//		return "/admin/admin_notice/admin_notice_update";
//	}
	
	@RequestMapping(value="/admin_notice_update.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid) {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_update");
		
		return mv;
	}
	
	/**
	 * adminNoticeUpdateCheck.do
	 */
	@RequestMapping(value="/adminUpdateNoticeCheck.do", method=RequestMethod.POST)
	public ModelAndView adminNoticeUpdateCheck(CgvNoticeVO vo) {
		ModelAndView mv = new ModelAndView();
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.update(vo);
		if(result == 1){
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{
			mv.setViewName("errorPage");
		}
		
		return mv;
	}
	
	/**
	 * admin_notice_delete.do
	 */
//	@RequestMapping(value="/admin_notice_delete.do", method=RequestMethod.GET)
//	public String admin_notice_delete() {
//		return "/admin/admin_notice/admin_notice_delete";
//	}
	
	@RequestMapping(value="/admin_notice_delete.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("nid", nid);
		mv.setViewName("/admin/admin_notice/admin_notice_delete");
		return mv;
	}
	
	/**
	 * adminDeleteNoticeCheck.do : 게시판 삭제 처리
	 */
	@RequestMapping(value="/adminDeleteNoticeCheck.do", method=RequestMethod.POST)
	public ModelAndView adminDeleteNoticeCheck(String nid) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.delete(nid);
		if(result == 1){
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{
			mv.setViewName("errorPage");
		}
		
		return mv;
	}
	
	/**
	 * admin_member_list.do
	 */
	@RequestMapping(value="/admin_member_list.do", method=RequestMethod.GET)
	public ModelAndView admin_member_list() {
		ModelAndView mv = new ModelAndView();
		
		CgvMemberDAO dao = new CgvMemberDAO();

		ArrayList<CgvMemberVO> list = dao.selectAll();
		
		mv.addObject("list", list);
		mv.setViewName("/admin/admin_member/admin_member_list");
		
		
		return mv;
	}
	
	/**
	 * admin_member_content.do
	 */
//	@RequestMapping(value="/admin_member_content.do", method=RequestMethod.GET)
//	public String admin_member_content() {
//		return "/admin/admin_member/admin_member_content";
//	}
	
	@RequestMapping(value="/admin_member_content.do", method=RequestMethod.GET)
	public ModelAndView admin_member_content(String id) {
		ModelAndView mv = new ModelAndView();

		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = dao.selectContent(id);
		String address = vo.getZonecode()+" "+vo.getAddr1()+" "+ vo.getAddr2();
		
		mv.addObject("vo", vo);
		mv.addObject("address", address);
		mv.setViewName("/admin/admin_member/admin_member_content");
		
		return mv;
	}
	
}
