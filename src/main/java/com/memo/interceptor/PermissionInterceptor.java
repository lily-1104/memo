package com.memo.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {
		
		// 요청 URL path를 꺼낸다
		String uri = request.getRequestURI();
		logger.info("[$$$$$$] preHandle. uri:{}", uri);
		
		// 로그인 여부
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 비로그인일 때 /post로 시작하는 주소로 들어오는 경우 => 로그인 페이지로 이동, 컨트롤러 수행 방지
		if (userId == null && uri.startsWith("/post")) {
			
			response.sendRedirect("/user/sign-in-view");
			return false;	// 컨트롤러 수행 안함 (원래 요청에 대해서)
		}
		
		// 로그인 상태에서 /user로 시작하는 주소로 들어오는 경우 => 글 목록 페이지 이동, 컨트롤러 수행 방지
		if (userId != null && uri.startsWith("/user")) {
			
			response.sendRedirect("/post/post-list-view");
			return false;	// 컨트롤러 수행 안함 (원래 요청에 대해서)
		}
		
		return true;	// controller 수행함
		
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView mav) {
		
		// view 객체가 mav로 존재한다는건 아직 jsp가 html로 변횐되기 전이다
		logger.info("[##### postHandle");
		
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Exception ex) {
		
		// jsp가 html로 최종 변환된 후
		logger.info("[@@@@@] afterCompletion");
		
	}
	

}
