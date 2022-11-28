package com.model2_cgv.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.model2_cgv.dao.CgvNoticeDAO;
import com.model2_cgv.vo.CgvNoticeVO;

@Controller
public class NoticeController {
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

		ArrayList<CgvNoticeVO> list = dao.select();
		
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
		
		return gson.toJson(jobject);
	}
	
	/**
	 * notice_content_json.do : 공지사항 상세정보를 Ajax로 호출
	 */
	@ResponseBody
	@RequestMapping(value="/notice_content_json.do", method=RequestMethod.GET
	 					, produces="text/plain;charset=UTF-8")
	public String notice_content_json(String nid) {

		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		if(vo != null){
			dao.updateHits(nid);
		}
		
		
		Gson gson = new Gson();
		JsonObject jobject = new JsonObject();
		jobject.addProperty("nid", vo.getNid());
		jobject.addProperty("ntitle", vo.getNtitle());
		jobject.addProperty("ncontent", vo.getNcontent());
		jobject.addProperty("nhits", vo.getNhits());
		jobject.addProperty("ndate", vo.getNdate());

		return gson.toJson(jobject); 
		
	}
}
