package com.model2_cgv.service;

import com.model2_cgv.vo.CgvBoardVO;

public interface BoardService {
	
	int getWriteResult(CgvBoardVO vo);		//�Խñ� ����
	CgvBoardVO getContent(String bid);		//�Խñ� �󼼺���
	void getUpdateHits(String bid);			//�Խñ� ��ȸ�� ������Ʈ
	int getUpdate(CgvBoardVO vo);			//�Խñ� ���� ó��
	int getDelete(String bid);				//�Խñ� ���� ó��
}