package com.model2_cgv.service;

import org.springframework.stereotype.Service;

import com.model2_cgv.dao.CgvMemberDAO;
import com.model2_cgv.vo.CgvMemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	/**
	 * 로그인 처리
	 */
	@Override
	public int getLoginResult(CgvMemberVO vo) {	
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.select(vo);		
		return result;
	}
	
	/** 
	 * 회원가입 처리
	 */
	@Override
	public int getJoinResult(CgvMemberVO vo) {	
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.insert(vo);		
		return result;
	}
	
	/**
	 * 회원 상세 정보
	 */
	@Override
	public CgvMemberVO getMemberContent(String id) {
		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = dao.selectContent(id);
		return vo;
	}
}
