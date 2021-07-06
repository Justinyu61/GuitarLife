package com.gl.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl.spring.exception.DLException;
import com.gl.spring.model.Customer;
import com.gl.spring.service.CustomerService;





@Controller
public class LoginController {
	 
	@Autowired
	private CustomerService service;
	
	
	@RequestMapping(value = "login",name = "用戶登入頁面...",method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request, HttpServletResponse response) {
		String errMsg = "";
		//request.setCharacterEncoding("utf-8");
		//1.從request取得form data:id ,pwd,captcha(暫時不用)
		String id = request.getParameter("id");
		String pwd =request.getParameter("pwd");
		String captcha = request.getParameter("captcha");
		System.out.println("驗證碼:"+captcha);
		
		HttpSession session = request.getSession();
		//檢查必要欄位與驗證碼
		if(id==null || id.length()==0) {
			errMsg = "必須輸入ID<br>";
		}
		if(pwd==null || pwd.length()==0) {
			errMsg += "必須輸入密碼<br>";
		}
		if(captcha==null || captcha.length()==0) {
			errMsg += "必須輸入驗證碼<br>";
		}else {
			String oldCaptcha =(String) session.getAttribute("captcha");
			if(!captcha.equalsIgnoreCase(oldCaptcha)) {
				errMsg += "驗證碼不正確";
			}
		}
		session.removeAttribute("captcha");
		
		return "login";
	}
	
	@RequestMapping(value = "login",name = "用戶登入...",method = RequestMethod.POST)
	public String loginStart(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		//2.呼叫商業邏輯:CustomerService物件的login 改寫
		CustomerService service = new CustomerService();
		
		try {
			Customer c = service.login(id, pwd);
			//this.log("會員登入成功")
			//3.1 forward 給成功的jsp(/login_ok.jsp)					
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login_ok.jsp");
			//session.setMaxInactiveInterval(10);//()裡設定秒數
		    //request.setAttribute("member", c);
			session.setAttribute("member", c);
		   dispatcher.forward(request, response);
			
			return ;
			
		} catch (DLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();//類似S.err.print(....),無法看到日期時間
			this.log(e.getMessage(), e);
			errMsg = e.getMessage();
		}
		//3.2 forward 到原來的login.html 
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		//request.setAttribute("error", errMsg);
		//dispatcher.forward(request, response);
		
		
	
		
		return "index";
	}
}

