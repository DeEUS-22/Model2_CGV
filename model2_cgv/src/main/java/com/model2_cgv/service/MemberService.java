package com.model2_cgv.service;

import java.util.ArrayList;

import com.model2_cgv.vo.CgvMemberVO;
import com.model2_cgv.vo.SessionVO;

public interface MemberService {
	SessionVO getLoginResult(CgvMemberVO vo);  //�α��� ó�� �޼ҵ�
	int getJoinResult(CgvMemberVO vo);	 //ȸ������ ó�� �޼ҵ�
	int getTotalCount();	//��ü ȸ���� 
	ArrayList<CgvMemberVO> getMemberList(int startCount, int endCount);  //ȸ�� ��ü ����Ʈ
	CgvMemberVO getMemberContent(String id);	//ȸ�� ������
}
