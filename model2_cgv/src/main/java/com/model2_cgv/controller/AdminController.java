package com.model2_cgv.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model2_cgv.dao.CgvMemberDAO;
import com.model2_cgv.service.FileServiceImpl;
import com.model2_cgv.service.MemberServiceImpl;
import com.model2_cgv.service.NoticeServiceImpl;
import com.model2_cgv.service.PageServiceImpl;
import com.model2_cgv.vo.CgvMemberVO;
import com.model2_cgv.vo.CgvNoticeVO;

@Controller
public class AdminController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	@Autowired
	private NoticeServiceImpl noticeService;
	
	@Autowired
	private FileServiceImpl fileService;
	
	@Autowired
	private PageServiceImpl pageService;
	
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
		
		Map<String, Integer> param = pageService.getPageResult(rpage, "notice", noticeService);

		ArrayList<CgvNoticeVO> list = noticeService.getList(param.get("startCount"),param.get("endCount"));
		
		mv.addObject("list",list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
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
	public ModelAndView admin_notice_write_check(CgvNoticeVO vo, HttpServletRequest request ) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		vo = fileService.fileCheck(vo);
		
		//DB연동
		int result = noticeService.getWriteResult(vo);
		
		if(result == 1){			
			fileService.fileSave(vo, request);
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
		CgvNoticeVO vo = noticeService.getContent(nid);
		
		if(vo != null){
			noticeService.getUpdateHits(nid);
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
		CgvNoticeVO vo = noticeService.getContent(nid);
		
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
		
		String old_filename = vo.getNsfile();	//수정화면에서 hidden으로 넘어오는 기존 upload 폴더에 저장된 파일명
		
		vo = fileService.update_fileCheck(vo);
		
		int result = noticeService.getUpdate(vo);
		
		if(result == 1){
			//새로운 파일을 upload 폴더에 저장한 후 기존의 파일은 삭제
			fileService.update_filesave(vo, request, old_filename);
		
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
	 * adminDeleteNoticeCheck.do : 게시판 삭제 처리
	 */
	@RequestMapping(value="/adminDeleteNoticeCheck.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_delete_check(String nid, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		CgvNoticeVO vo = noticeService.getContent(nid);
		int result = noticeService.getDelete(nid);
	
		if(result == 1){	
			fileService.fileDelete(vo, request);
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
		
		Map<String, Integer> param = pageService.getPageResult(rpage, "member", memberService);
		ArrayList<CgvMemberVO> list = memberService.getMemberList(param.get("startCount"), param.get("endCount"));
		
		mv.addObject("list", list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
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
