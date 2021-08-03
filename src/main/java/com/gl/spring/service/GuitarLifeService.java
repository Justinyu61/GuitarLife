package com.gl.spring.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.spring.DAO.CustomerDAO;
import com.gl.spring.DAO.OrderDAO;
import com.gl.spring.DAO.ProductDAO;
import com.gl.spring.entity.Customer;
import com.gl.spring.entity.Order;
import com.gl.spring.entity.Product;

@Service
public class GuitarLifeService {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private OrderDAO orderDao;
	
	//使用getCustom取得Customer裡的customer物件物件
	public Customer getCustomer(String id) {
		Optional<Customer> c = customerDao.selectCustomersById(id);//c物件連線DAO
		Customer customer = new Customer();//建立customer物件
		if(c.isPresent()) {//如果c這個物件有取得到Customer資料
			customer = c.get();//將此物件取得的資料放進customer裡
		}
		return customer;
	}
		
	//寫登入的檢查狀態
	public enum LoginStatus{
		SUCCESS("登入成功"),
		ACCOUNT_NOT_ACTIVE("帳號尚未啟用"),
		ACCOUNT_NOT_FOUND("帳號尚未註冊"),
		WRONG_PASSWORD("密碼錯誤");
		
		private String loginStatus;
		
		LoginStatus(String loginStatus){
			this.loginStatus = loginStatus;
		}
		
		String getDescription() {
			return this.loginStatus;
		}
	}
	
	//寫入判斷
	public LoginStatus login(String id,String pwd) {
		Optional<Customer> c = customerDao.selectCustomersById(id);
		
		LoginStatus loginstat = null;
		if(c.isPresent()) {//如果c這個物件有取得到Customer資料
			Customer customer = c.get();
			if(!customer.isCorrectPWD(pwd)) {//如果密碼輸入錯誤
				loginstat = LoginStatus.WRONG_PASSWORD;//loginstat則代表"密碼錯誤"的狀態並印出		
			}else {
				loginstat = LoginStatus.SUCCESS;//其餘 "登入成功"
			}
		}else {//其餘"帳號尚未註冊"
			loginstat = LoginStatus.ACCOUNT_NOT_FOUND;
		}
		logger.log(Level.INFO, "[" + id + "]登入結果 :" + loginstat.getDescription());
		return loginstat;
	}
	
	public enum RegisterStatus{
		SUCCESS("註冊成功"), 
		NOT_SUCCESS("註冊不成功"), 
		ID_NOT_RIGHT("帳號格式錯誤"), 
		EMAIL_NOT_RIGHT("E-mail輸入錯誤"),
		PASSWORD_NOT_RIGHT("密碼格式錯誤"), 
		NAME_NOT_RIGHT("名字輸入錯誤"), 
		GENDER_NOT_RIGHT("請輸入性別"), 
		BIRTHDAY_NOT_RIGHT("生日輸入錯誤");
		
		private String registerStatus;
		
		RegisterStatus(String registerStatus){
			this.registerStatus = registerStatus;
		}
		
	    String getDescription() {
	    	return this.registerStatus;
	    }		
	}
	
	public RegisterStatus rsgister(String id, String email , String pwd1, String pwd2,
			String name, String gender, String birthday, String phone, String address, 
			String subscribed) {
		logger.log(Level.INFO, "registerService");
		RegisterStatus regstat = null;
		if (id == null || id.length() == 0) {
			regstat = RegisterStatus.ID_NOT_RIGHT;
		} else if (email == null || (email = email.trim()).length() == 0) {
			regstat = RegisterStatus.EMAIL_NOT_RIGHT;
		} else if (pwd1 == null || pwd1.length() == 0 || !pwd1.equals(pwd2)) {
			regstat = RegisterStatus.PASSWORD_NOT_RIGHT;
		} else if (name == null || (name = name.trim()).length() == 0) {
			regstat = RegisterStatus.NAME_NOT_RIGHT;
		} else if (gender == null || !gender.matches("[MF]")) {
			regstat = RegisterStatus.GENDER_NOT_RIGHT;
		} else if (birthday == null || birthday.length() == 0) {
			regstat = RegisterStatus.BIRTHDAY_NOT_RIGHT;
		} else {
			Customer inc = new Customer();
			inc.setId(id);
			inc.setEmail(email);
			inc.setPassword(pwd1);
			inc.setPassword(pwd2);
			inc.setName(name);
			inc.setGender(gender.charAt(0));
			inc.setBirthday(birthday);

			inc.setPhone(phone);
			inc.setAddress(address);
			inc.setSubscribed(subscribed != null);

			int c = customerDao.insertCustomer(inc);
			if(c == 1) {
				regstat = RegisterStatus.SUCCESS;
			}else {
				regstat = RegisterStatus.NOT_SUCCESS;
			}			
		}
		logger.log(Level.INFO, "[" + id + "]註冊結果:" + regstat.getDescription());

		return regstat;
	}

	public List<Product> getProducts(){
		List<Product> productList = productDao.selectAllProducts();				 
//		Product p1 = new Product();//測試用
		
//		if(!productList.isEmpty()) {
			
//		p1.setId(123);
//		p1.setName("aaa");
//		p1.setBrand("bbb");
//		p1.setUnitPrice(455);
//		p1.setStock(56);
//		p1.setDescription("");
//		p1.setPhotoUrl("");
//		p1.setType("yyy");
//		
//		productList.add(p1);
//		}		
		return productList;
	}
	
	public Product getProductId(String id){
		Optional<Product> productId = productDao.selectProductsById(id);
		Product p1 = new Product();
		
		if(!productId.isEmpty()) {				
			p1 = productId.get();			
		}		
		return p1;
	}
	
	public List<Product> getProductsByBrand(String brand){
		List<Product> productByBrand = productDao.selectProductsByBrand(brand);
		
		return productByBrand;
	}
	
	public Order getOrderById(String orderId){
		Optional<Order> orderById = orderDao.selectOrderById(orderId);
		Order o1 = new Order();
		
		if(!orderById.isEmpty()) {
//			o1.setId(1);
//			
//			o1.setCreatedDate("createdDate");
//			o1.setCreatedTime("createdTime");
//			o1.setStatus(0);
//			
//			o1.setPaymentType("123");
//			o1.setPaymentFee(123);
//			o1.setPaymentNote("123666");
//			o1.setShippingType("船運");
//			o1.setShippingFee(123);
//			o1.setShippingNote("123666");
//			
//			o1.setRecipientName("Justin");
//			o1.setRecipientEmail("mmm@yyy.com");
//			o1.setRecipientPhone("0988888888");
//			o1.setShippingAddress("ji3ji3ji3ji3ji3");
			
			
			o1 = orderById.get();
		}			
		return o1;
	}
	
	

			
}
