package com.model2_cgv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.model2_cgv.vo.SessionVO;

public class AuthInterceptor extends HandlerInterceptorAdapter{ // HandlerInterceptorAdapter 이게 부모 -> 부모로 부터 상속받음.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
									throws Exception {
		
		//세션을 체크하는 로직 구현
		//1. request 객체를 통해 session 정보를 가져오기
		HttpSession session = request.getSession(); 
		
		//2. 로그인 성공 시 session에 로그인 인증키(sid) 담아 클라이언트에게 전송
		SessionVO svo = (SessionVO)session.getAttribute("svo");  //로그인한 계정:자신의 ID, 로그인하지 않은 사용자: null
		
		if(svo == null) {			
			//로그인하지 않은 사용자: null
			response.sendRedirect("http://localhost:9000/model2_cgv/login.do?auth=fail");
			
			return false;
		}else {
			//sid가 null이 아니고, admin 인 경우에만 접속
			if(!svo.getId().equals("admin")) {
				response.sendRedirect("http://localhost:9000/model2_cgv/login.do?auth=fail");
				return false;
			}
		}		
		
			return true;
	}
}
