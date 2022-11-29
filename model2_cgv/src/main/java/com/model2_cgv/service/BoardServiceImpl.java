package com.model2_cgv.service;

import org.springframework.stereotype.Service;

import com.model2_cgv.dao.CgvBoardDAO;
import com.model2_cgv.vo.CgvBoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	/**
	 * �Խñ� ����
	 */
	@Override
	public int getWriteResult(CgvBoardVO vo) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/**
	 * �Խñ� �󼼺���
	 */
	@Override
	public CgvBoardVO getContent(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		return vo;
	}		
	
	/**
	 * ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		dao.updateHits(bid);
	}
	
	/**
	 * ����ó��
	 */
	@Override
	public int getUpdate(CgvBoardVO vo) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.update(vo);
		return result;
	}
	
	/**
	 * ���� ó��
	 */
	@Override
	public int getDelete(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.delete(bid);
		return result;
	}
}
