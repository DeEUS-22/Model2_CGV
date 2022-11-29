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

import com.model2_cgv.dao.CgvBoardDAO;
import com.model2_cgv.service.BoardServiceImpl;
import com.model2_cgv.vo.CgvBoardVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardService;
	
	/**
	 * board_list.do
	 */
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public ModelAndView board_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		//화면에서 전송된 페이지요청 번호 가져오기
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
	 * board_write.do : 게시판 글쓰기 화면
	 */
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}
	
	/**
	 * board_write.do
	 */
	@RequestMapping(value="/boardWriteCheck.do", method=RequestMethod.POST)
	public ModelAndView board_write_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if(vo.getFile1().getOriginalFilename().equals("")) {
			vo.setBfile("");
			vo.setBsfile("");
		}else {
			UUID uuid = UUID.randomUUID();
			vo.setBfile(vo.getFile1().getOriginalFilename());
			vo.setBsfile(uuid + "_" + vo.getFile1().getOriginalFilename());
		}
		
		int result = boardService.getWriteResult(vo);
		
		if(result == 1){
			if(!vo.getFile1().getOriginalFilename().equals("")) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
				
				File file = new File(path+vo.getBsfile());
				vo.getFile1().transferTo(file);
			}
			
			mv.setViewName("redirect:/board_list.do"); //DB연동을 Controller에서 진행하므로, 새로운 연결을 수행!!
		}else{
			mv.setViewName("errorPage");
		}
		
		return mv;
	}
	
	/**
	 * board_content.do
	 */
	@RequestMapping(value="/board_content.do", method=RequestMethod.GET)
	public ModelAndView board_content(String bid) {
		ModelAndView mv = new ModelAndView();
		
		CgvBoardVO vo = boardService.getContent(bid);
		if(vo != null){
			boardService.getUpdateHits(bid);
		}
		
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_content");
		
		return mv;
	}
	
	/**
	 * board_update.do
	 */
	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
	public ModelAndView board_update(String bid) {
		ModelAndView mv = new ModelAndView();
		CgvBoardVO vo = boardService.getContent(bid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_update");
		
		return mv;
	}
	
	
	/**
	 * boardUpdateCheck.do
	 */
	@RequestMapping(value="/boardUpdateCheck.do", method=RequestMethod.POST)
	public ModelAndView board_update_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//기존 파일이 존재하는 경우 이름을 변수로 저장
		String old_filename = vo.getBsfile();
		
		//수정시 새로운 파일을 선택했는지 확인
		if(!vo.getFile1().getOriginalFilename().equals("")) { //새로운 파일선택 O
			
			UUID uuid = UUID.randomUUID();
			
			vo.setBfile(vo.getFile1().getOriginalFilename());
			vo.setBsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
		}				
		
		int result = boardService.getUpdate(vo);
		
		if(result == 1){
			//새로운 파일을 upload 폴더에 저장
			if(!vo.getFile1().getOriginalFilename().equals("")) { //새로운 파일선택 O
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
				
				File file = new File(path+vo.getBsfile());
				vo.getFile1().transferTo(file);
			
				//기존파일이 있는 경우에는 파일 삭제
				File ofile = new File(path+old_filename);
				if(ofile.exists()) {
					ofile.delete();
				}
			}
			
			
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
	public ModelAndView board_delete_check(String bid, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//삭제할 bid 행에 bsfile의 이름을 가져오기 위해 dao.select(bid) 메소드 호출--> upload폴더에 파일 유무 확인
		CgvBoardVO vo = boardService.getContent(bid); //dao.select(bid) 메소드는 delete 메소드 호출 전에 실행되어야함!! 
		int result = boardService.getDelete(bid);
		
		if(result == 1){
			//if(!vo.getBsfile().equals("")) {
			if(vo.getBsfile() != null) {
				String path=request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
				
				File old_file = new File(path+vo.getBsfile());
				if(old_file.exists()) {
					old_file.delete();
				}
			}
			mv.setViewName("redirect:/board_list.do");
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
}
