package com.model2_cgv.service;

import org.springframework.stereotype.Service;

import com.model2_cgv.dao.CgvNoticeDAO;
import com.model2_cgv.vo.CgvNoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{
	/**
	 * �������� �۾��� ó��
	 */
	@Override
	public int getWriteResult(CgvNoticeVO vo) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/**
	 * �������� �󼼺���
	 */
	@Override
	public CgvNoticeVO getContent(String nid) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		return vo;
	}
	
	/**
	 * �������� ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String nid) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		dao.updateHits(nid);
	}
	
	/**
	 * �������� ���� ó��
	 */
	@Override
	public int getUpdate(CgvNoticeVO vo) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.update(vo);
		return result;
	}
	
	/**
	 * �������� ���� ó��
	 */
	@Override
	public int getDelete(String nid) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.delete(nid);
		return result;
	}
}
