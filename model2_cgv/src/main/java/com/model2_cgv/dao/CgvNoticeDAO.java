package com.model2_cgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model2_cgv.vo.CgvNoticeVO;

@Repository
public class CgvNoticeDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "mapper.notice";
	
	/**
	 * insert : 공지사항 등록
	 */
	public int insert(CgvNoticeVO vo) {
		return sqlSession.insert(namespace+".insert", vo);
	}
	
	/**
	 * select : 전체 공지사항 리스트
	 */
	public ArrayList<CgvNoticeVO> select(int startCount, int endCount){
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<CgvNoticeVO> list = sqlSession.selectList(namespace+".list", param);
		
		return (ArrayList<CgvNoticeVO>)list;
	}
	
	/**
	 * select : 공지사항 상세보기
	 */
	public CgvNoticeVO select(String nid) {
		return sqlSession.selectOne(namespace+".content",nid);
	}
	
	/**
	 * updateHits : 조회수 업데이트 
	 */
	public void updateHits(String nid) {
		sqlSession.update(namespace+".updatehits",nid);		
	}
	
	/**
	 * update : 공지사항 업데이트 
	 */
	public int update(CgvNoticeVO vo) {
		return sqlSession.update(namespace+".update", vo);
	}
	
	/**
	 * delete : 공지사항 삭제 
	 */
	public int delete(String nid) {
		return sqlSession.delete(namespace+".delete",nid);
	}
	
	/**
	 * totalCount : 전체 로우수 출력
	 */
	public int totalCount() {
		return sqlSession.selectOne(namespace + ".totalcount");
	}
	
}
