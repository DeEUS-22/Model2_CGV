package com.model2_cgv.service;

import com.model2_cgv.vo.CgvMemberVO;

public interface MemberService {
	int getLoginResult(CgvMemberVO vo);  //로그인 처리 메소드
	int getJoinResult(CgvMemberVO vo);	 //회원가입 처리 메소드
	CgvMemberVO getMemberContent(String id);	//회원 상세정보
}
