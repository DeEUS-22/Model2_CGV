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
	 * �α��� ó��
	 */
	@Override
	public SessionVO getLoginResult(CgvMemberVO vo) {	
		return memberDao.select(vo);		
	}
	
	/** 
	 * ȸ������ ó��
	 */
	@Override
	public int getJoinResult(CgvMemberVO vo) {	
		return memberDao.insert(vo);
	}
	
	/**
	 * ȸ�� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvMemberVO> getMemberList(int startCount, int endCount){	
		ArrayList<CgvMemberVO> list = memberDao.selectAll(startCount, endCount);
		
		return list;
	}
	
	/**
	 * ȸ�� ��ü��
	 */
	@Override
	public int getTotalCount() {
		return memberDao.totalCount();
	}
	
	/**
	 * ȸ�� �� ����
	 */
	@Override
	public CgvMemberVO getMemberContent(String id) {
		return memberDao.selectContent(id);
	}
}
