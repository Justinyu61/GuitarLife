package com.gl.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.spring.exception.DLDataInvalidateException;
import com.gl.spring.exception.DLException;
import com.gl.spring.model.Customer;
import com.gl.spring.service.CustomerService;




//RestController是用來測試資料庫連線是否正常
//@RestController與@Controller差別在於,@RestController註解中有包含了@ResponseBody的註解
@Controller
public class LoginController {
	 
	@Autowired 
	private CustomerService service;		
	
	
	@RequestMapping(value = "login",name = "用戶登入頁面",method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request, HttpServletResponse response) {		
		
		return "login";
	}
	
	@RequestMapping(value = "login.start",name = "用戶登入",method = RequestMethod.POST)
	public String loginStart(HttpServletRequest request, HttpServletResponse response) {
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
			errMsg = "必須輸入ID,";
		}
		if(pwd==null || pwd.length()==0) {
			errMsg += "必須輸入密碼,";
		}					
		//2.呼叫商業邏輯:CustomerService物件的login 改寫
		if(errMsg.length()>0) {
			return "login";
		}else{
			try{//改為optional用法
				  Optional<Customer> c = service.login(id, pwd);
					if(c.isPresent()) {
						session.setAttribute("member", c.get());//c.get()代表從Optional這個包裝裡取出Customer這個物件
					   }					   
					return "index";//
		         } catch (Exception e) {//只抓取最大的Exception,只要有異常就拋出到login的頁面
				// TODO Auto-generated catch block
				e.printStackTrace();//類似S.err.print(....),無法看到日期時間			
				errMsg = e.getMessage();			
			return "login";
		  }
		}									
	}
}

