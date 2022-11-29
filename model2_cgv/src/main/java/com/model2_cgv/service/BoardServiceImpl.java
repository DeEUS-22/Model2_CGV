package com.model2_cgv.service;

import org.springframework.stereotype.Service;

import com.model2_cgv.dao.CgvBoardDAO;
import com.model2_cgv.vo.CgvBoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	/**
	 * 게시글 쓰기
	 */
	@Override
	public int getWriteResult(CgvBoardVO vo) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/**
	 * 게시글 상세보기
	 */
	@Override
	public CgvBoardVO getContent(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		return vo;
	}		
	
	/**
	 * 조회수 업데이트
	 */
	@Override
	public void getUpdateHits(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		dao.updateHits(bid);
	}
	
	/**
	 * 수정처리
	 */
	@Override
	public int getUpdate(CgvBoardVO vo) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.update(vo);
		return result;
	}
	
	/**
	 * 삭제 처리
	 */
	@Override
	public int getDelete(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.delete(bid);
		return result;
	}
}
