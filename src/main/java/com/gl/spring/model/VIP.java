package com.gl.spring.model;

import org.springframework.stereotype.Component;

@Component
public class VIP extends Customer {

    

	private int discount = 5;//0%~95% off,預設8折扣數 

	public int getDiscount() {
		return discount;
	} 

	public void setDiscount(int discount) {
		if(discount>=0 && discount<=95) {
		this.discount = discount;
		}else {
			System.err.println("VIP折扣不正確");
		}
	}
	public String getDiscountString() {
	//getDiscountString()代表中文字串
		//String discountString;
		int discount = 100- this.discount;
//		 if(discount%10==0) {
//			 return discount/10+"折";
//		 }
		return discount + "折";//8折
		
		

	}

	@Override
	public String toString() {
		return "VIP 折扣" + discount + "%"; 
	}
	
	
}
   //public String VIPLvTwo;


  // public String VIPLvthree;

