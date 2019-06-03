package com.cafe24.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.jblog.vo.UserVo;



public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	//이 메서드에서 Null이 리턴이되면 컨트롤러에서 받는 @AuthUserVo에 null이 들어가게 되어서 이 핸들러에서 처리를안함? UNRESOVED 저건 빈객체가 리턴되는데 그럼 처리함
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		if( supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		//@AuthUser가 안붙어이쓤 ㅇㅅㅇ
		if(authUser == null) {
			return false;// 다른애가 처리해줌
		}
		
		//@AuthUser라고 해놓고 UserVo authUser 가 아니라 BoardVo boardVo라고 변수선언을 이상하게 할 수 도있으니 타입도 검사
		
		if(parameter.getParameterType().equals(UserVo.class)) {
			//클래스객체비교
			return false;
		}
		return false;
	}
}
