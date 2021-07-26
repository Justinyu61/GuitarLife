package com.gl.spring.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.spring.DAO.CustomerDAO;
import com.gl.spring.entity.Customer;
import com.google.protobuf.Empty;

@Service
public class GuitarLifeService {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private CustomerDAO dao;
	
	public Customer getCustomer(String id) {
		Optional<Customer> c = dao.selectCustomerById(id);
		Customer customer = new Customer();
		if(c.isPresent()) {
			customer = c.get();
		}
		return customer;
	}

	public enum LoginStatus {
		SUCCESS("登入成功"), 
		ACCOUNT_NOT_ACTIVE("帳號尚未啟用"), 
		ACCOUNT_NOT_FOUND("帳號尚未註冊"), 
		WRONG_PASSWORD("密碼輸入錯誤");

		private String loginStatus;

		LoginStatus(String loginStatus) {
			this.loginStatus = loginStatus;
		}

		String getDescription() {
			return this.loginStatus;
		}
	}

	public LoginStatus login(String id, String pwd) {
		Optional<Customer> c = dao.selectCustomerById(id);

		LoginStatus logsta = null;
		if (c.isPresent()) {
			Customer customer = c.get();
			if (!customer.isCorrectPWD(pwd)) {
				logsta = LoginStatus.WRONG_PASSWORD;
//			} else if (!customer.isActive()) {
//				result = LoginStatus.ACCOUNT_NOT_ACTIVE;
			} else {
				logsta = LoginStatus.SUCCESS;
			}
		} else {
			logsta = LoginStatus.ACCOUNT_NOT_FOUND;
		}
		logger.log(Level.INFO, "[" + id + "]登入結果:" + logsta.getDescription());
		return logsta;
	}

	public enum RegisterStatus {
		SUCCESS("註冊成功"), 
		NOT_SUCCESS("註冊不成功"), 
		ID_NOT_RIGHT("帳號格式錯誤"), 
		EMAIL_NOT_RIGHT("E-mail輸入錯誤"),
		PASSWORD_NOT_RIGHT("密碼格式錯誤"), 
		NAME_NOT_RIGHT("名字輸入錯誤"), 
		GENDER_NOT_RIGHT("請輸入性別"), 
		BIRTHDAY_NOT_RIGHT("生日輸入錯誤");

		private String registerStatus;

		RegisterStatus(String registerStatus) {
			this.registerStatus = registerStatus;
		}

		String getDescription() {
			return this.registerStatus;
		}
	}

	public RegisterStatus register(String id, String email, String pwd1, String pwd2, String name, String gender,
			String birthday, String phone, String address, String subscribed) {
		logger.log(Level.INFO, "registerService");

		RegisterStatus regsta = null;
		if (id == null || id.length() == 0) {
			regsta = RegisterStatus.ID_NOT_RIGHT;
		} else if (email == null || (email = email.trim()).length() == 0) {
			regsta = RegisterStatus.EMAIL_NOT_RIGHT;
		} else if (pwd1 == null || pwd1.length() == 0 || !pwd1.equals(pwd2)) {
			regsta = RegisterStatus.PASSWORD_NOT_RIGHT;
		} else if (name == null || (name = name.trim()).length() == 0) {
			regsta = RegisterStatus.NAME_NOT_RIGHT;
		} else if (gender == null || !gender.matches("[MF]")) {
			regsta = RegisterStatus.GENDER_NOT_RIGHT;
		} else if (birthday == null || birthday.length() == 0) {
			regsta = RegisterStatus.BIRTHDAY_NOT_RIGHT;
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

			int c = dao.insertCustomer(inc);
			if(c == 1) {
				regsta = RegisterStatus.SUCCESS;
			}else {
				regsta = RegisterStatus.NOT_SUCCESS;
			}			
		}
		logger.log(Level.INFO, "[" + id + "]註冊結果:" + regsta.getDescription());

		return regsta;
	}


}
