package com.model2_cgv.service;

import com.model2_cgv.vo.CgvMemberVO;

public interface MemberService {
	int getLoginResult(CgvMemberVO vo);  //�α��� ó�� �޼ҵ�
	int getJoinResult(CgvMemberVO vo);	 //ȸ������ ó�� �޼ҵ�
	CgvMemberVO getMemberContent(String id);	//ȸ�� ������
}
