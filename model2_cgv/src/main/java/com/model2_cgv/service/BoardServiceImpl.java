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
	 * �Խñ� ����
	 */
	@Override
	public int getWriteResult(CgvBoardVO vo) {
		return boardDao.insert(vo);
	}
	
	/**
	 * �Խñ� �󼼺���
	 */
	@Override
	public CgvBoardVO getContent(String bid) {
		return boardDao.select(bid);
	}	
	
	/**
	 * ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String bid) {
		boardDao.updateHits(bid);
	}
	
	/**
	 * ����ó��
	 */
	@Override
	public int getUpdate(CgvBoardVO vo) {
		return boardDao.update(vo);
	}
	
	/**
	 * ���� ó��
	 */
	@Override
	public int getDelete(String bid) {
		return boardDao.delete(bid);
	}
	
	/**
	 * �Խñ� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvBoardVO> getList(int startCount, int endCount){
		ArrayList<CgvBoardVO> list = boardDao.select(startCount, endCount);
		return list;
	}
	
	/**
	 * �Խñ� ��ü �ο�
	 */
	@Override
	public int getTotalCount() {
		return boardDao.totalCount();
	}
	
}
