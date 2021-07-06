package com.gl.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gl.spring.exception.DLException;
import com.gl.spring.model.Customer;


@Service
public class CustomerService {
	@Autowired
	private CustomerDAO dao;
	public Customer login(String id,String pwd)throws DLException{
		   if(id==null || pwd==null) {
			  throw new IllegalArgumentException("登入時id或pwd不得為null"); 		   
		   }
		   Customer c = dao.selectCustomerById(id);
		   if(c!=null){
			 if(pwd.equals(c.getPassword())) {
				return c; 
			 }
		   }
		   throw new DLException("登入失敗,帳號或密碼不正確");
		   
	    }
	   
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
