package com.model2_cgv.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model2_cgv.dao.CgvBoardDAO;
import com.model2_cgv.vo.CgvBoardVO;

@Controller
public class BoardController {
	/**
	 * board_list.do
	 */
//	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
//	public String board_list() {
//		return "/board/board_list";
//	}
	
//	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
//	public ModelAndView board_list() {
//		ModelAndView mv = new ModelAndView();
//		CgvBoardDAO dao = new CgvBoardDAO();

//		ArrayList<CgvBoardVO> list = dao.select();
		
//		mv.addObject("list", list);
//		mv.setViewName("/board/board_list");

//		return mv;
//	}
	
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public ModelAndView board_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		//화면에서 전송된 페이지요청 번호 가져오기
		//String rpage = request.getParameter("rpage"); 
		CgvBoardDAO dao = new CgvBoardDAO();

		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 3;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = dao.totalCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//요청 페이지 계산
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}

		ArrayList<CgvBoardVO> list = dao.select(startCount, endCount);
		
		mv.addObject("list", list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("rpage", reqPage);
		mv.setViewName("/board/board_list");
		
		return mv;
	}
	
	
	/**
	 * board_write.do
	 */
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}
	
	/**
	 * boardWriteCheck.do
	 */
	@RequestMapping(value="/boardWriteCheck.do", method=RequestMethod.POST)
	public ModelAndView boardWriteCheck(CgvBoardVO vo) {
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.insert(vo);
		
		if(result == 1) {
			mv.setViewName("redirect:/board_list.do");
		}else {
			mv.setViewName("errorPage");
		}
		
		return mv;
	}
	
	/**
	 * board_content.do
	 */
//	@RequestMapping(value="/board_content.do", method=RequestMethod.GET)
//	public String board_content() {
//		return "/board/board_content";
//	}
	
	/**
	 * board_content.do
	 */
	@RequestMapping(value="/board_content.do", method=RequestMethod.GET)
	public ModelAndView board_content(String bid) {
		ModelAndView mv = new ModelAndView();
		
		//String bid = request.getParameter("bid");
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		if(vo != null){
			dao.updateHits(bid);
		}
		
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_content");
		
		return mv;
	}
	
	/**
	 * board_update.do
	 */
//	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
//	public String board_update() {
//		return "/board/board_update";
//	}
	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
	public ModelAndView board_update(String bid) {
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_update");
		
		return mv;
	}
	
	
	/**
	 * boardUpdateCheck.do
	 */
	@RequestMapping(value="/boardUpdateCheck.do", method=RequestMethod.POST)
	public ModelAndView boardUpdateCheck(CgvBoardVO vo) {
		ModelAndView mv = new ModelAndView();
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.update(vo);
		if(result == 1){
			mv.setViewName("redirect:/board_list.do");
		}else{
			mv.setViewName("errorPage");
		}
		
		return mv;
	}
	
	/**
	 * board_delete.do : 게시판 삭제 화면
	 */
	@RequestMapping(value="/board_delete.do", method=RequestMethod.GET)
	public ModelAndView board_delete(String bid) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bid", bid);
		mv.setViewName("/board/board_delete");
		
		return mv;
	}
	
	/**
	 * board_delete_check.do : 게시판 삭제 처리
	 */
	@RequestMapping(value="/boardDeleteCheck.do", method=RequestMethod.POST)
	public ModelAndView board_delete_check(String bid) {
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.delete(bid);
		if(result == 1){
			mv.setViewName("redirect:/board_list.do");
		}else{
			mv.setViewName("errorPage");
		}
		
		return mv;
	}
	
}
