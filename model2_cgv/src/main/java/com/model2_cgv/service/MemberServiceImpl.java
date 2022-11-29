package com.model2_cgv.service;

import org.springframework.stereotype.Service;

import com.model2_cgv.dao.CgvMemberDAO;
import com.model2_cgv.vo.CgvMemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	/**
	 * �α��� ó��
	 */
	@Override
	public int getLoginResult(CgvMemberVO vo) {	
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.select(vo);		
		return result;
	}
	
	/** 
	 * ȸ������ ó��
	 */
	@Override
	public int getJoinResult(CgvMemberVO vo) {	
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.insert(vo);		
		return result;
	}
	
	/**
	 * ȸ�� �� ����
	 */
	@Override
	public CgvMemberVO getMemberContent(String id) {
		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = dao.selectContent(id);
		return vo;
	}
}
