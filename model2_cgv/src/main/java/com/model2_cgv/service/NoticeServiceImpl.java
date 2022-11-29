package com.model2_cgv.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2_cgv.dao.CgvNoticeDAO;
import com.model2_cgv.vo.CgvNoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private CgvNoticeDAO noticeDao;
	
	/**
	 * 공지사항 글쓰기 처리
	 */
	@Override
	public int getWriteResult(CgvNoticeVO vo) {
		return noticeDao.insert(vo);
	}
	
	/**
	 * 공지사항 상세보기
	 */
	@Override
	public CgvNoticeVO getContent(String nid) {
		return noticeDao.select(nid);
	}
	
	/**
	 * 공지사항 조회수 업데이트
	 */
	@Override
	public void getUpdateHits(String nid) {
		noticeDao.updateHits(nid);
	}
	
	/**
	 * 공지사항 수정 처리
	 */
	@Override
	public int getUpdate(CgvNoticeVO vo) {
		return noticeDao.update(vo);
	}
	
	/**
	 * 공지사항 삭제 처리
	 */
	@Override
	public int getDelete(String nid) {
		return noticeDao.delete(nid);
	}
	
	/**
	 * 공지사항 전체 리스트
	 */
	@Override
	public ArrayList<CgvNoticeVO> getList(int startCount, int endCount){
		return noticeDao.select(startCount,endCount);
	}
	
	/**
	 * 공지사항 전체 로우수
	 */
	@Override
	public int getTotalCount() {
		return noticeDao.totalCount();
	}
}
