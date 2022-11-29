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
	 * �������� �۾��� ó��
	 */
	@Override
	public int getWriteResult(CgvNoticeVO vo) {
		return noticeDao.insert(vo);
	}
	
	/**
	 * �������� �󼼺���
	 */
	@Override
	public CgvNoticeVO getContent(String nid) {
		return noticeDao.select(nid);
	}
	
	/**
	 * �������� ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String nid) {
		noticeDao.updateHits(nid);
	}
	
	/**
	 * �������� ���� ó��
	 */
	@Override
	public int getUpdate(CgvNoticeVO vo) {
		return noticeDao.update(vo);
	}
	
	/**
	 * �������� ���� ó��
	 */
	@Override
	public int getDelete(String nid) {
		return noticeDao.delete(nid);
	}
	
	/**
	 * �������� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvNoticeVO> getList(int startCount, int endCount){
		return noticeDao.select(startCount,endCount);
	}
	
	/**
	 * �������� ��ü �ο��
	 */
	@Override
	public int getTotalCount() {
		return noticeDao.totalCount();
	}
}
