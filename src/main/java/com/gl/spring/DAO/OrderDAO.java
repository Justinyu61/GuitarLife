package com.gl.spring.DAO;

import java.util.List;
import java.util.Optional;

import com.gl.spring.entity.Order;

public interface OrderDAO {
	 
	Optional<Order> selectOrderById(String orderId);
	
	public void insertOrder(Order order);
	
	//List<Order> selectOrderHistoryByMemberId(String memberId);
	
	

}
