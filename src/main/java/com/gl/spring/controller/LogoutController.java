package com.gl.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl.spring.model.Customer;

@Controller
public class LogoutController {
	@RequestMapping(value = "logout",name = "用戶登出",method = RequestMethod.GET)
	public String loguotPage(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return "logout";
	}
	@RequestMapping(value = "logout",name = "用戶成功登出",method = RequestMethod.POST)
	public String logoutOk(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);//false是不要幫我建立新的的意思,取得舊session
		if(session!=null) {
			Customer c =(Customer)session.getAttribute("member");
			//session.removeAttribute("member");//這裡用removeAttribute來完成登出邏輯(做法1)
			session.invalidate();//這裡用invalidate來完成登出邏輯(做法2)
			request.setAttribute("log.out.member", c);
		}
		
		
		return "index";
	}

}
