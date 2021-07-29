package com.gl.spring.DAO;

import java.util.List;

import com.gl.spring.entity.Order;

public class OrderDAO {
	 
	Order selectOrderById(String orderId);
	
	public int insertOrder(Order order);
	
	List<Order> selectOrderHistoryByMemberId(String memberId);
	
	

}
