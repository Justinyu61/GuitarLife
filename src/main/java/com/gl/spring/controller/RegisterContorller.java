package com.gl.spring.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@Controller
public class RegisterContorller {
	@RequestMapping(value = "register",name = "註冊",method = RequestMethod.GET)
	public String registerPage(HttpServletRequest request, HttpServletResponse response){
		
	
	return "register";
	}
	@RequestMapping(value = "register",name = "註冊",method = RequestMethod.POST)
	public String registeOk(HttpServletRequest request, HttpServletResponse response) {
        List<String> errorMsgs=new ArrayList<>();
		
		
		//1.取得request中的form data:id,email,pwd1,pwd2,name,gender,birthday
		//address,phone,
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String pwd1 = request.getParameter("pwd1");
		String pwd2 = request.getParameter("pwd2");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String subscribed = request.getParameter("subscribed");
		String captcha = request.getParameter("captcha");
	    //檢查必要欄位
		if(id==null || id.length()==0){
		  errorMsgs.add("必須輸入帳號");
	     }
      	if(email==null || (email=email.trim()).length()==0){
	      errorMsgs.add("必須輸入email");
         }
        if(pwd1==null || pwd1.length()==0 ||!pwd1.equals(pwd2)){
          errorMsgs.add("必須輸入密碼");
         }          
        if(name==null || (name=name.trim()).length()==0){
          errorMsgs.add("必須輸入姓名");
         }
        if(gender==null|| !gender.matches("[MF]") ) {
        	errorMsgs.add("必須輸入性別");
         }
        
        if(birthday==null || birthday.length()==0){
          errorMsgs.add("必須輸入生日");
         }
        if(captcha==null || captcha.length()==0){
          errorMsgs.add("必須輸入驗證碼");
         }else{
        	 //TODO:chap15:驗證碼比對    
         }
//         System.out.println("地址"+address);
//         System.out.println("電話"+phone);
//         System.out.println("訂閱"+subscribed);
//          System.out.println(errorMsgs);
          

		
	return "index";	
	}
}
