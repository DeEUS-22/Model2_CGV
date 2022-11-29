package com.model2_cgv.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model2_cgv.dao.CgvMemberDAO;
import com.model2_cgv.dao.CgvNoticeDAO;
import com.model2_cgv.service.MemberServiceImpl;
import com.model2_cgv.vo.CgvMemberVO;
import com.model2_cgv.vo.CgvNoticeVO;

@Controller
public class AdminController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
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
	@RequestMapping(value="/admin_notice_list.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();

		//startCount, endCount
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//���������� �Խù� ��
		int reqPage = 1;	//��û������	
		int pageCount = 1;	//��ü ������ ��
		int dbCount = dao.totalCount();	//DB���� ������ ��ü ���
		
		//�� ������ �� ���
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//��û ������ ���
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}

		ArrayList<CgvNoticeVO> list = dao.select(startCount, endCount);
		
		mv.addObject("list",list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("rpage", reqPage);
		mv.addObject("pageSize", pageSize);
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
	 * admin_notice_write_check.do : �������� �۾��� ó��
	 */
	@RequestMapping(value="/adminNoticeWriteCheck.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_write_check(CgvNoticeVO vo, HttpServletRequest request ) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if(vo.getFile1().getOriginalFilename().equals("")) {
			vo.setNfile("");
			vo.setNsfile("");
		}else {
			UUID uuid = UUID.randomUUID();
		
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
		
		}
		
		//DB����
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.insert(vo);
		if(result == 1){			
			if(!vo.getFile1().getOriginalFilename().equals("")) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
		
				File file = new File(path+vo.getNsfile());
				vo.getFile1().transferTo(file);
		}
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{
			mv.setViewName("errorPage");
		}		
		
		return mv;
	}
	
	/**
	 * admin_notice_content.do
	 */
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
	public ModelAndView admin_notice_update_check(CgvNoticeVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String old_filename = vo.getNsfile();	//����ȭ�鿡�� hidden���� �Ѿ���� ���� upload ������ ����� ���ϸ�
		
		if(!vo.getFile1().getOriginalFilename().equals("")) {  //���ο� ������ ������ ���
			UUID uuid = UUID.randomUUID();
		
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
		}
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.update(vo);
		if(result == 1){
			//���ο� ������ upload ������ ������ �� ������ ������ ����
			if(!vo.getFile1().getOriginalFilename().equals("")) {  //���ο� ������ ������ ���
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
		
				File new_file = new File(path+vo.getNsfile());
				vo.getFile1().transferTo(new_file);
		
				//���� ���� ����
				File old_file = new File(path+old_filename);
				if(old_file.exists()) {
					old_file.delete();
				}				
			}
		
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{
		
			mv.setViewName("errorPage");
		}		
		
		return mv;
	}
	
	/**
	 * admin_notice_delete.do
	 */
	@RequestMapping(value="/admin_notice_delete.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("nid", nid);
		mv.setViewName("/admin/admin_notice/admin_notice_delete");
		return mv;
	}
	
	/**
	 * adminDeleteNoticeCheck.do : �Խ��� ���� ó��
	 */
	@RequestMapping(value="/adminDeleteNoticeCheck.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_delete_check(String nid, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		//DB����
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		int result = dao.delete(nid);
	
		if(result == 1){	
			if(vo.getNsfile() != null) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
	
				File old_file = new File(path + vo.getNsfile());
				if(old_file.exists()) {
					old_file.delete();
				}
			}
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
	public ModelAndView admin_member_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		//String rpage = request.getParameter("rpage");
		CgvMemberDAO dao = new CgvMemberDAO();
		
		//startCount, endCount
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//���������� �Խù� ��
		int reqPage = 1;	//��û������	
		int pageCount = 1;	//��ü ������ ��
		int dbCount = dao.totalCount();	//DB���� ������ ��ü ���
		
		//�� ������ �� ���
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//��û ������ ���
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}


		ArrayList<CgvMemberVO> list = dao.selectAll(startCount, endCount);
		
		mv.addObject("list", list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("rpage", reqPage);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("/admin/admin_member/admin_member_list");
		
		
		return mv;
	}
	
	/**
	 * admin_member_content.do
	 */
	@RequestMapping(value="/admin_member_content.do", method=RequestMethod.GET)
	public ModelAndView admin_member_content(String id) {
		ModelAndView mv = new ModelAndView();

		CgvMemberVO vo = memberService.getMemberContent(id);
		String address = vo.getZonecode()+" "+vo.getAddr1()+" "+ vo.getAddr2();
		
		mv.addObject("vo", vo);
		mv.addObject("address", address);
		mv.setViewName("/admin/admin_member/admin_member_content");
		
		return mv;
	}
	
}
