package com.model2_cgv.service;

import java.util.ArrayList;

import com.model2_cgv.vo.CgvMemberVO;
import com.model2_cgv.vo.SessionVO;

public interface MemberService {
	SessionVO getLoginResult(CgvMemberVO vo);  //로그인 처리 메소드
	int getJoinResult(CgvMemberVO vo);	 //회원가입 처리 메소드
	int getTotalCount();	//전체 회원수 
	ArrayList<CgvMemberVO> getMemberList(int startCount, int endCount);  //회원 전체 리스트
	CgvMemberVO getMemberContent(String id);	//회원 상세정보
}
