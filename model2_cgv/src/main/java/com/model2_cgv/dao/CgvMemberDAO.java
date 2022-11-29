package com.model2_cgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model2_cgv.vo.CgvMemberVO;
import com.model2_cgv.vo.SessionVO;

@Repository
public class CgvMemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * insert : ȸ������ 
	 */
	public int insert(CgvMemberVO vo) {
		//sqlSession ��ü�� �޼ҵ带 ȣ���Ͽ� �������� ������
		return sqlSession.insert("mapper.member.join", vo);
	}//insert-end
	
	/**
	 * select : �α���
	 */
	public SessionVO select(CgvMemberVO vo) {
		return sqlSession.selectOne("mapper.member.login",vo);
	}	
	
	/**
	 * selectAll : ȸ�� ��ü ����Ʈ
	 */
	public ArrayList<CgvMemberVO> selectAll(int startCount, int endCount){		
		//Map Ÿ���� ��ü�� �����ϰ� start, end��� Ű�̸����� �Ķ���͸� �ѱ��.
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<CgvMemberVO> list = sqlSession.selectList("mapper.member.listAll", param);
		
		return (ArrayList<CgvMemberVO>)list;
	}
	
	/**
	 * selectContent : ȸ�� �� ����
	 */
	public CgvMemberVO selectContent(String id) {
		return sqlSession.selectOne("mapper.member.content", id);	
	}
	
	/**
	 * totalCount : ��ü �ο�� ���
	 */
	public int totalCount() {		
		return sqlSession.selectOne("mapper.member.total_count");
	}
	
}//CgvMemberDAO-end
