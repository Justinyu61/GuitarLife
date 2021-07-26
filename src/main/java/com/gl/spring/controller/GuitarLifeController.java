package com.gl.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl.spring.entity.Customer;
import com.gl.spring.service.GuitarLifeService;
import com.gl.spring.service.GuitarLifeService.LoginStatus;
import com.gl.spring.service.GuitarLifeService.RegisterStatus;

@Controller
public class GuitarLifeController {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private GuitarLifeService service;

	
	@GetMapping("/login")
	public String loginPag(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.INFO, "載入登入畫面");
		return "login";
	}


	@PostMapping("/login")
	public String loginStart(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		List<String> errMsg = new ArrayList<String>();
		if (id == null || id.length() == 0) {
			errMsg.add("必須輸入ID,");
		}
		if (pwd == null || pwd.length() == 0) {
			errMsg.add("必須輸入密碼,");
		}
		if (!errMsg.isEmpty()) {
			request.setAttribute("errMsg", errMsg);
			return "login";
		}
		try {
			LoginStatus lostatus = service.login(id, pwd);
			if (lostatus == LoginStatus.SUCCESS) {
				Customer member = service.getCustomer(id);
				
				HttpSession session = request.getSession();				
				session.setAttribute("member", member);
				return "redirect:/index";
			} else {
				if (lostatus == LoginStatus.ACCOUNT_NOT_ACTIVE) {
					errMsg.add("帳號未啟用");
				}
				if (lostatus == LoginStatus.ACCOUNT_NOT_FOUND || lostatus == LoginStatus.WRONG_PASSWORD) {
					errMsg.add("帳號或密碼錯誤");
				}
				request.setAttribute("errMsg", errMsg);
				return "login";
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			request.setAttribute("errMsg", Arrays.asList("系統錯誤，請稍後再試"));
			return "login";
		}

	}

	
	@GetMapping("/register")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.INFO, "載入註冊畫面");
		
		return "register";
	}

	
	@PostMapping("/register")
	public String registerStart(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.INFO, "register.start");
		

		// 1.取得request中的form data:id,email,pwd1,pwd2,name,gender,birthday
		// address,phone,
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

		List<String> errorMsgs = new ArrayList<>();
		// 檢查必要欄位
		if (id == null || id.length() == 0) {
			errorMsgs.add("必須輸入帳號");
		}
		if (email == null || (email = email.trim()).length() == 0) {
			errorMsgs.add("必須輸入email");
		}
		if (pwd1 == null || pwd1.length() == 0 || !pwd1.equals(pwd2)) {
			errorMsgs.add("必須輸入密碼");
		}
		if (name == null || (name = name.trim()).length() == 0) {
			errorMsgs.add("必須輸入姓名");
		}
		if (gender == null || !gender.matches("[MF]")) {
			errorMsgs.add("必須輸入性別");
		}
		if (birthday == null || birthday.length() == 0) {
			errorMsgs.add("必須輸入生日");
//         }
//        if(captcha==null || captcha.length()==0){
//          errorMsgs.add("必須輸入驗證碼");
		}
//		if (errorMsgs.size() != 0){//size是int型別,但是在if()是布林型別所以要用 == 0 或 != 0做判斷裡面有沒有東西(或用.isEmpty())
			
		if(!errorMsgs.isEmpty()) {
			request.setAttribute("errors", errorMsgs);
			return "redirect:/login";
		}
		// 2.若無誤,呼叫商業邏輯	
		try {
			
			RegisterStatus resatuts = service.register(id, email, pwd1, pwd2, name, gender, birthday, phone, address, subscribed);			
			
			if(resatuts == RegisterStatus.SUCCESS) {
				
				request.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession();
				session.setAttribute("customer", Boolean.TRUE);
				
			}else {
				
				if(resatuts == RegisterStatus.ID_NOT_RIGHT) {
					errorMsgs.add("帳號格式錯誤");
				}
				if(resatuts == RegisterStatus.EMAIL_NOT_RIGHT) {
					errorMsgs.add("E-mail輸入錯誤");
				}
				if(resatuts == RegisterStatus.PASSWORD_NOT_RIGHT) {
					errorMsgs.add("密碼格式錯誤");
				}
				if(resatuts == RegisterStatus.NAME_NOT_RIGHT) {
					errorMsgs.add("名字輸入錯誤");
				}
				if(resatuts == RegisterStatus.GENDER_NOT_RIGHT) {
					errorMsgs.add("請輸入性別");
				}
				if(resatuts == RegisterStatus.BIRTHDAY_NOT_RIGHT) {
					errorMsgs.add("生日輸入錯誤");
				}
				
			} 
		}catch(Exception e){
			logger.log(Level.SEVERE, e.getMessage() ,e);
			request.setAttribute("errors", Arrays.asList("系統錯誤，請稍後再試"));
			return "redirect:/login";
		}
			
		return "index";
	}

	
	@GetMapping("/index")
	public String indexPage(HttpServletRequest request) {
		logger.log(Level.INFO, "載入首頁畫面");
		return "index";
	}

	@RequestMapping(value = "logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}


}
