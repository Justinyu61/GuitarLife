package com.gl.modeltest;

import java.time.LocalDate;
import com.gl.spring.model.*;


public class TestCustomer {

	public static void main(String[] args) {
		int i = 1;
	Customer customer ;//類別名稱小寫//一定要先指派 new *****()
    customer = Customer();
	//customer.id=new String("A123456789");
	
	customer.setId("A123456789");
	
	//customer.id="A123456789";//無法編譯,customer.id已經改為private
//	System.out.println("customer.id:"+customer.id);
//	System.out.println("customer.id:"+customer.getId());//A123456789
	customer.setEmail("xxx@uuu.com.tw");
//	System.out.println("customer.email:"+customer.getEmail());
	//customer.password="6~20個字元";
	customer.setPassword("justinyu61");
//	System.out.println("customer.password:"+customer.getPassword());
	customer.setName("JustinYu");
//	System.out.println("customer.name:"+customer.getName());
	
	customer.setGender('M');
//	System.out.println("customer.gender:"+customer.getGender());
	
	customer.setBirthday(LocalDate.of(2001, 03, 20));//LocalDate後面一定要加.of 才可以寫入日期數字
	//System.out.println("customer.birthday:"+customer.getBirthday());//2001-03-20(iso 8601)	
	
	customer.setBirthday(LocalDate.now());		 
	//System.out.println("customer.birthday:"+customer.getBirthday());//2020-12-29(iso 8601),當下日期
	
	customer.setBirthday(LocalDate.parse("2001-03-20"));		
	//System.out.println("customer.birthday:"+customer.getBirthday());//2001-03-20(iso 8601),
	
	customer.setPhone("09xx-xxx-xxx");
//	System.out.println("customer.phone:"+customer.getPhone());
	customer.setAddress(" ");
//	System.out.println("customer.address:"+customer.getAddress());
	customer.setSubscribed(true);
//	System.out.println("customer.subscribed:"+customer.subscribed);
	
	System.out.println("會員:"+customer);
	}

	private static Customer Customer() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
