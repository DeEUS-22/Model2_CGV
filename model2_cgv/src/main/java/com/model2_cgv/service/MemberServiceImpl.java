package com.model2_cgv.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2_cgv.dao.CgvMemberDAO;
import com.model2_cgv.vo.CgvMemberVO;
import com.model2_cgv.vo.SessionVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private CgvMemberDAO memberDao;
	
	/**
	 * 로그인 처리
	 */
	@Override
	public SessionVO getLoginResult(CgvMemberVO vo) {	
		return memberDao.select(vo);		
	}
	
	/** 
	 * 회원가입 처리
	 */
	@Override
	public int getJoinResult(CgvMemberVO vo) {	
		return memberDao.insert(vo);
	}
	
	/**
	 * 회원 전체 리스트
	 */
	@Override
	public ArrayList<CgvMemberVO> getMemberList(int startCount, int endCount){	
		ArrayList<CgvMemberVO> list = memberDao.selectAll(startCount, endCount);
		
		return list;
	}
	
	/**
	 * 회원 전체수
	 */
	@Override
	public int getTotalCount() {
		return memberDao.totalCount();
	}
	
	/**
	 * 회원 상세 정보
	 */
	@Override
	public CgvMemberVO getMemberContent(String id) {
		return memberDao.selectContent(id);
	}
}
