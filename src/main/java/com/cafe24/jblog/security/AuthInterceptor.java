package com.cafe24.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

//User식별 어노테이션
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. 관심있는것이 Controller에 있는 메서드이므로 HandlerMethod 타입인지 체크.
		// handler 종류 확인
		
		if(handler instanceof HandlerMethod == false) {
			
			return true; //컨트롤러에 있는 메서드가 아니므로 그대로 컨트롤러 진행시킴.
		}
		
		//2. 확인이 끝나면 형 변환
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. @Auth를 받아옴
		AuthAdmin auth = handlerMethod.getMethodAnnotation(AuthAdmin.class);
		
		//4. method에 @Auth가 없는 경우, 인증이 필요 없는 요청
		if(auth == null) {
			return true;
		}
		String[] uri = request.getRequestURI().split("/");
		String blogid = uri[2];

		//5.@4번이 통과됬다는거는 auth가 있는 경우이므로 세션이 있는지 체크
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/"+blogid);
			return false;
		}
		
		//6. 5번도 통과면 세션이 존재한다는 소리 유효한 유저인지부터 확인
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() +"/"+blogid);
			return false;
		}
		
		if(blogid.equals(authUser.getId()) == false) {
		
			response.sendRedirect(request.getContextPath() +"/"+blogid);
			return false;
		}
		
		return true;
	}

	
}
