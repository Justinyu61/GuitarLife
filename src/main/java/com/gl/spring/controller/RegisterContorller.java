package com.gl.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl.spring.exception.DLDataInvalidateException;
import com.gl.spring.exception.DLException;
import com.gl.spring.model.Customer;
import com.gl.spring.service.CustomerService;




@Controller
public class RegisterContorller {
		@RequestMapping(value = "register",name = "註冊",method = RequestMethod.GET)
		public String register(HttpServletRequest request, HttpServletResponse response){
		
		return "register";
	}
		@RequestMapping(value = "register.start",name = "註冊",method = RequestMethod.POST)
		public String registerStart(HttpServletRequest request, HttpServletResponse response) {
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
	//		String captcha = request.getParameter("captcha");
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
	//         }
	//        if(captcha==null || captcha.length()==0){
	//          errorMsgs.add("必須輸入驗證碼");
	         }
	          //2.若無誤,呼叫商業邏輯
	          if(errorMsgs.isEmpty()) {
	        	  Customer c = new Customer();
	        	  try {
		        		  c.setId(id);
		        		  c.setEmail(email);
		        		  c.setPassword(pwd1);
		        		  c.setName(name);
		        		  c.setGender(gender.charAt(0));
		        		  c.setBirthday(birthday);
		        		  
		        		  c.setAddress(address);
		        		  c.setPhone(phone);
		        		  c.setSubscribed(subscribed!=null);
		        		  
		        		  CustomerService service = new CustomerService();
		        		  service.register(c);
		        		  
		        		  request.setAttribute("customer", c);
		        		  
		        		  return "login";
		        		//TODO: send 註冊成功的 email		             		  
//		        	  }catch(DLDataInvalidateException e) {        		 
//		        		  errorMsgs.add(e.getMessage());
//		        	  } catch (DLException e) {				
//						  errorMsgs.add(e.getMessage());
					  } catch (Exception e) {				 
						  errorMsgs.add("發生非預期錯誤"+e);//給客戶看d					 
					  }          	       
	            }       	
	          return "index";	                	
	}	
}
