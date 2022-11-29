package com.model2_cgv.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2_cgv.dao.CgvBoardDAO;
import com.model2_cgv.vo.CgvBoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private CgvBoardDAO  boardDao;
	
	/**
	 * 게시글 쓰기
	 */
	@Override
	public int getWriteResult(CgvBoardVO vo) {
		return boardDao.insert(vo);
	}
	
	/**
	 * 게시글 상세보기
	 */
	@Override
	public CgvBoardVO getContent(String bid) {
		return boardDao.select(bid);
	}	
	
	/**
	 * 조회수 업데이트
	 */
	@Override
	public void getUpdateHits(String bid) {
		boardDao.updateHits(bid);
	}
	
	/**
	 * 수정처리
	 */
	@Override
	public int getUpdate(CgvBoardVO vo) {
		return boardDao.update(vo);
	}
	
	/**
	 * 삭제 처리
	 */
	@Override
	public int getDelete(String bid) {
		return boardDao.delete(bid);
	}
	
	/**
	 * 게시글 전체 리스트
	 */
	@Override
	public ArrayList<CgvBoardVO> getList(int startCount, int endCount){
		ArrayList<CgvBoardVO> list = boardDao.select(startCount, endCount);
		return list;
	}
	
	/**
	 * 게시글 전체 로우
	 */
	@Override
	public int getTotalCount() {
		return boardDao.totalCount();
	}
	
}
