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
	 * insert : �������� ���
	 */
	public int insert(CgvNoticeVO vo) {
		return sqlSession.insert(namespace+".insert", vo);
	}
	
	/**
	 * select : ��ü �������� ����Ʈ
	 */
	public ArrayList<CgvNoticeVO> select(int startCount, int endCount){
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<CgvNoticeVO> list = sqlSession.selectList(namespace+".list", param);
		
		return (ArrayList<CgvNoticeVO>)list;
	}
	
	/**
	 * select : �������� �󼼺���
	 */
	public CgvNoticeVO select(String nid) {
		return sqlSession.selectOne(namespace+".content",nid);
	}
	
	/**
	 * updateHits : ��ȸ�� ������Ʈ 
	 */
	public void updateHits(String nid) {
		sqlSession.update(namespace+".updatehits",nid);		
	}
	
	/**
	 * update : �������� ������Ʈ 
	 */
	public int update(CgvNoticeVO vo) {
		return sqlSession.update(namespace+".update", vo);
	}
	
	/**
	 * delete : �������� ���� 
	 */
	public int delete(String nid) {
		return sqlSession.delete(namespace+".delete",nid);
	}
	
	/**
	 * totalCount : ��ü �ο�� ���
	 */
	public int totalCount() {
		return sqlSession.selectOne(namespace + ".totalcount");
	}
	
}
