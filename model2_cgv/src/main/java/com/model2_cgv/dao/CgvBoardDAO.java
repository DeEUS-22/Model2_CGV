package com.model2_cgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model2_cgv.vo.CgvBoardVO;

@Repository
public class CgvBoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * insert : �Խñ� ���
	 */
	public int insert(CgvBoardVO vo) {
		return sqlSession.insert("mapper.board.insert", vo);
	}
	
	/**
	 * select : �Խñ� ��ü ����Ʈ
	 */
	public ArrayList<CgvBoardVO> select(int startCount, int endCount){
		
		//�Ķ���͸� Map���� �����ϱ�
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("start", startCount);	
		param.put("end", endCount);
		
		List<CgvBoardVO> list = sqlSession.selectList("mapper.board.list", param);
		
		
		return (ArrayList<CgvBoardVO>)list;
	}
	
	
	/**
	 * select : �Խñ� �� ����
	 */
	public CgvBoardVO select(String bid) {
		return sqlSession.selectOne("mapper.board.content",bid);
	}
	
	/**
	 * updateHits : ��ȸ�� ������Ʈ
	 */
	public int updateHits(String bid) {
		return sqlSession.update("mapper.board.updatehits",bid);
	}
	
	/**
	 * update : �Խñ� ���� 
	 */
	public int update(CgvBoardVO vo) {		
		return sqlSession.update("mapper.board.update",vo);
	}
	
	/**
	 * delete : �Խñ� ����
	 */
	public int delete(String bid) {
		return sqlSession.delete("mapper.board.delete",bid);
	}
	
	/**
	 * totalCount : ��ü �ο�� ���
	 */
	public int totalCount() {
		return sqlSession.selectOne("mapper.board.totalcount");
	}
}
