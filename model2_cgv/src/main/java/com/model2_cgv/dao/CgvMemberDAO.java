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
	 * insert : 회원가입 
	 */
	public int insert(CgvMemberVO vo) {
		//sqlSession 객체의 메소드를 호출하여 실행결과를 가져옴
		return sqlSession.insert("mapper.member.join", vo);
	}//insert-end
	
	/**
	 * select : 로그인
	 */
	public SessionVO select(CgvMemberVO vo) {
		return sqlSession.selectOne("mapper.member.login",vo);
	}	
	
	/**
	 * selectAll : 회원 전체 리스트
	 */
	public ArrayList<CgvMemberVO> selectAll(int startCount, int endCount){		
		//Map 타입의 객체를 생성하고 start, end라는 키이름으로 파라미터를 넘긴다.
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<CgvMemberVO> list = sqlSession.selectList("mapper.member.listAll", param);
		
		return (ArrayList<CgvMemberVO>)list;
	}
	
	/**
	 * selectContent : 회원 상세 정보
	 */
	public CgvMemberVO selectContent(String id) {
		return sqlSession.selectOne("mapper.member.content", id);	
	}
	
	/**
	 * totalCount : 전체 로우수 출력
	 */
	public int totalCount() {		
		return sqlSession.selectOne("mapper.member.total_count");
	}
	
}//CgvMemberDAO-end
