package com.model2_cgv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.model2_cgv.vo.SessionVO;

public class MycgvAuthInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
									throws Exception {
		
		//������ üũ�ϴ� ���� ����
		//1. request ��ü�� ���� session ������ ��������
		HttpSession session = request.getSession();
		
		//2. �α��� ���� �� session�� �α��� ����Ű(sid) ��� Ŭ���̾�Ʈ���� ����
		SessionVO svo = (SessionVO)session.getAttribute("svo");  //�α����� ����:�ڽ��� ID, �α������� ���� �����: null
		if(svo == null) {			
			//�α������� ���� �����: null
			response.sendRedirect("http://localhost:9000/model2_cgv/login.do?auth=fail");
			
			return false;
		}
		
			return true;
	}
}
