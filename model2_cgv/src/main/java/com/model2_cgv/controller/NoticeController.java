package com.model2_cgv.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.model2_cgv.dao.CgvNoticeDAO;
import com.model2_cgv.service.NoticeServiceImpl;
import com.model2_cgv.vo.CgvNoticeVO;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeServiceImpl noticeService;
	
	/**
	 * notice_list.do : 공지사항 전체 리스트 
	 */
	@RequestMapping(value="/notice_list.do", method=RequestMethod.GET)
	public String notice_list() {
		return "/notice/notice_list";
	}
	
	/**
	 * notice_list_json.do : 공지사항 전체 리스트를 Ajax로 호출
	 */
	@ResponseBody
	@RequestMapping(value="/notice_list_json.do", method=RequestMethod.GET
	 					, produces="text/plain;charset=UTF-8")
	public String notice_list_json(String rpage) {
		
		//DB에서 공지사항 전체 리스트 가져오기
		CgvNoticeDAO dao = new CgvNoticeDAO();

		//startCount, endCount
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
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


		ArrayList<CgvNoticeVO> list = dao.select(startCount,endCount);
		
		//gson 라이브러리를 이용하여 자바 list 객체를 JSON 객체로 변환
		JsonObject jobject = new JsonObject(); //CgvNoticeVO
		JsonArray jarray = new JsonArray();  //ArrayList
		Gson gson = new Gson();
		
		for(CgvNoticeVO vo : list){
			JsonObject jo = new JsonObject();
			jo.addProperty("rno", vo.getRno());
			jo.addProperty("nid", vo.getNid());
			jo.addProperty("ntitle", vo.getNtitle());
			jo.addProperty("ndate", vo.getNdate());
			jo.addProperty("nhits", vo.getNhits());
			
			jarray.add(jo);
		}
		
		jobject.add("list", jarray); 
		jobject.addProperty("dbCount", dbCount);
		jobject.addProperty("pageSize", pageSize);
		jobject.addProperty("rpage", reqPage);
		jobject.addProperty("pageCount", pageCount);
		
		return gson.toJson(jobject);
	}
	
	/**
	 * notice_content_json.do : 공지사항 상세정보를 Ajax로 호출
	 */
	@ResponseBody
	@RequestMapping(value="/notice_content_json.do", method=RequestMethod.GET
	 					, produces="text/plain;charset=UTF-8")
	public String notice_content_json(String nid) {

		CgvNoticeVO vo = noticeService.getContent(nid);
		
		if(vo != null){
			noticeService.getUpdateHits(nid);
		}
		
		Gson gson = new Gson();
		JsonObject jobject = new JsonObject();
		jobject.addProperty("nid", vo.getNid());
		jobject.addProperty("ntitle", vo.getNtitle());
		jobject.addProperty("ncontent", vo.getNcontent());
		jobject.addProperty("nhits", vo.getNhits());
		jobject.addProperty("ndate", vo.getNdate());
		jobject.addProperty("nfile", vo.getNfile());
		jobject.addProperty("nsfile", vo.getNsfile());

		return gson.toJson(jobject); 
		
	}
}
