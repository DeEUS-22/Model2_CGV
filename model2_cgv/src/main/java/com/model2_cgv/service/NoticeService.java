package com.model2_cgv.service;

import com.model2_cgv.vo.CgvNoticeVO;

public interface NoticeService {
	int getWriteResult(CgvNoticeVO vo);		//�������� �۾��� ó��
	CgvNoticeVO getContent(String nid);	//�������� �󼼺���
	void getUpdateHits(String nid);     //�������� ��ȸ�� ������Ʈ
	int getUpdate(CgvNoticeVO vo);		//�������� ���� ó��
	int getDelete(String nid);		//�������� ���� ó��
}
