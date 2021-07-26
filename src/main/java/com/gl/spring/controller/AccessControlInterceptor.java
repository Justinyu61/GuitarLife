package com.gl.spring.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AccessControlInterceptor implements HandlerInterceptor{

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	
	public boolean preHandler(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Boolean hasLogin = (Boolean) session.getAttribute("hasLogin");
		if(Boolean.TRUE == hasLogin) {
			logger.info("assess uri :" + request.getRequestURI());
			return true;
		}else {
			response.sendRedirect(request.getContextPath() + "login");
			return false;
		}
	}

}
