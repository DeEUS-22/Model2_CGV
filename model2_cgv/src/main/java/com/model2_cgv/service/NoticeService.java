package com.model2_cgv.service;

import com.model2_cgv.vo.CgvNoticeVO;

public interface NoticeService {
	int getWriteResult(CgvNoticeVO vo);		//공지사항 글쓰기 처리
	CgvNoticeVO getContent(String nid);	//공지사항 상세보기
	void getUpdateHits(String nid);     //공지사항 조회수 업데이트
	int getUpdate(CgvNoticeVO vo);		//공지사항 수정 처리
	int getDelete(String nid);		//공지사항 삭제 처리
}
