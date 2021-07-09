package com.gl.spring.service;

import java.lang.reflect.Member;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.spring.exception.DLException;
import com.gl.spring.model.Customer;



@Service
public class CustomerService {
	@Autowired
	private CustomersDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param pwd
	 * @return 有註冊的使用者
	 * @throws DLException
	 */
	public Optional<Customer> login(String id,String pwd)throws DLException{//用optional<回傳值>,至少不會發生NPE
			
		   if(id==null || pwd==null) {
			  throw new IllegalArgumentException("登入時id或pwd不得為null"); 		   
		   }
		   Customer c = dao.selectCustomerById(id);//準備去DAO選取Customer ID Pwd,去判斷是不是null
		   if(c!=null){
			 if(pwd.equals(c.getPassword())) {
				return Optional.of(c);//Optional.of()代表裡面回傳的物件不是空的 
			 }
		   }
		   return Optional.empty();//不要用DLException控制流程,所以用Optional.empty()回傳一個空的包裹		   
	    }
	   
		//註冊驗證
	   public void register(Customer customer) throws DLException{
		   if(customer==null) {
			   throw new IllegalArgumentException("會員註冊時不得為null");
		   }
		dao.insert(customer);
	}

	public void update(Customer customer) throws DLException {
		 if(customer==null) {
			   throw new IllegalArgumentException("會員註冊時不得為null");
		   }
		dao.update(customer);
	}

}
