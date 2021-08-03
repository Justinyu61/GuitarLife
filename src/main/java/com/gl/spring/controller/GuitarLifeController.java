package com.gl.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
import com.gl.spring.entity.Order;
import com.gl.spring.entity.Product;
import com.gl.spring.entity.ShoppingCart;
import com.gl.spring.service.GuitarLifeService;
import com.gl.spring.service.GuitarLifeService.LoginStatus;
import com.gl.spring.service.GuitarLifeService.RegisterStatus;
import com.mysql.cj.Session;




@Controller
public class GuitarLifeController {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private GuitarLifeService service;
	
	
	
	@GetMapping("/login")
	public String loginPage(HttpServletRequest request,HttpServletResponse response) {
		//logging.log(Level.INFO,"載入登入畫面");
		return"login";
	}
	
	@PostMapping("/login")
	public String loginStart(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		List<String> errMsg = new ArrayList<String>();
		if(id == null || id.length() ==0) {//如果ID不適null或ID是空的話
			errMsg.add("必須輸入帳號");
		}
		if(pwd == null || pwd.length() == 0) {//如果PWD不適null或ID是空的話
			errMsg.add("必須輸入密碼");
		}
		if(!errMsg.isEmpty()) {//如果任何錯誤訊息不是空的(0)就代表有誤並回login
			request.setAttribute("errMsg", errMsg);
			return "login";
		}
		try {LoginStatus loginstat = service.login(id, pwd);//帶入login()此方法
			//寫入判斷
			if(loginstat == LoginStatus.SUCCESS) {//如果登入狀態為成功則帶入此Customer
				Customer member = service.getCustomer(id);//將member指定為service的getCustomer(id)
				
				HttpSession session = request.getSession();//取得session連線
				session.setAttribute("member", member);//取得連線後將member帶到網頁
				return "redirect:/index";
			}else {//如果沒找到此member則跑下面的判斷
				if(loginstat == LoginStatus.ACCOUNT_NOT_ACTIVE) {
					errMsg.add("帳尚未啟用");
				}
				if(loginstat == LoginStatus.ACCOUNT_NOT_FOUND || loginstat == LoginStatus.WRONG_PASSWORD) {
					errMsg.add("帳號或密碼錯誤");
				}
				request.setAttribute("errMsg", errMsg);
				return "login";
			}
		}catch (Exception e){//如果連線未成功則拋出錯誤
			logger.log(Level.SEVERE, e.getMessage(), e);
			request.setAttribute("errMsg", Arrays.asList("系統錯誤,請稍後在試"));
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
		}	
		if(!errorMsgs.isEmpty()) {
			request.setAttribute("errors", errorMsgs);
			return "redirect:/login";
		}
			try {			
			RegisterStatus resatuts = service.rsgister(id, email, pwd1, pwd2, name, gender, birthday, phone, address, subscribed);			
			
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
	
	@GetMapping("/header")
	public String header(HttpServletRequest request,HttpServletResponse response) {
		logger.log(Level.INFO, "載入Header");
		return "header";
	}
	
	@GetMapping("/products")
	public String products(HttpServletRequest request) {		
		List<Product> p = service.getProducts();
		request.setAttribute("products", p);//要把一些資訊送到網頁頁面上
		
		for(Product product : p) {
			logger.log(Level.INFO,"產品明細" + product.getId()+ "," + product.getName()+ "," + product.getBrand()+ "," + product.getUnitPrice()+ "," + product.getPhotoUrl());
		}	
		logger.log(Level.INFO, "載入產品頁面");
		return "products";
	}
	
	@GetMapping("/productinfo")
	public String product(HttpServletRequest request) {			
		String id = request.getParameter("id");				
		Product prod = service.getProductId(id);
		request.setAttribute("prodInfo", prod);	
		
		if(prod != null) {				
			logger.log(Level.INFO,"產品細節 : " + prod);
			return "productinfo";
		}else{
			logger.log(Level.INFO, "查無此產品");
			return "products";
		}				
	}
	
	@GetMapping("/productbrand")
	public String prodbrand(HttpServletRequest request) {
		String brand = request.getParameter("brand");//取得從網頁上傳入的參數
		//之後要做格式的判斷
		List<Product> p = service.getProductsByBrand(brand);
		//localhost:8080/GuitarLife/productbrand?brand=Taylor
		request.setAttribute("products", p);
		request.setAttribute("brand", brand);
		//可以寫logger					
		return "products";
	}
	
	
	
	@GetMapping("/getcart")
	public String shoppingCart(HttpServletRequest request) {
		
		class ProdInfo{
			private String productId;
			private String unitPrice;
			private String quantity;
			private String subtotal;
			
			public String getProductId() {
				return productId;
			}
			public void setProductId(String productId) {
				this.productId = productId;
			}
			public String getUnitPrice() {
				return unitPrice;
			}
			public void setUnitPrice(String unitPrice) {
				this.unitPrice = unitPrice;
			}
			public String getQuantity() {
				return quantity;
			}
			public void setQuantity(String quantity) {
				this.quantity = quantity;
			}
			public String getSubtotal() {
				return subtotal;
			}
			public void setSubtotal(String subtotal) {
				this.subtotal = subtotal;
			}						
		}				
		
		//取得從網頁上傳入的參數
		ProdInfo prodInfo = new ProdInfo();
		prodInfo.setProductId(request.getParameter("productId"));
		prodInfo.setUnitPrice(request.getParameter("unitPrice"));
		prodInfo.setQuantity(request.getParameter("quantity"));
		prodInfo.setSubtotal(request.getParameter("subtotal"));
		
		HttpSession session = request.getSession(); 
		session.setAttribute("Cart",prodInfo.getProductId()+prodInfo.getUnitPrice()+prodInfo.getQuantity()+prodInfo.getSubtotal());
		//String prodInfo = request.getParameter("prodInfo");		
		
			logger.log(Level.INFO, "購物車畫面");
			
		return "shoppingcart";
	}
	
	
	
	@GetMapping("/order")
	public String order(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");	
		Order o = service.getOrderById(orderId);
		request.setAttribute("orderId", o);
		
		if(o != null) {
			logger.log(Level.INFO, "訂單" + o);
			return "order";
		}
		return "order";
				
	}
	
	


}
