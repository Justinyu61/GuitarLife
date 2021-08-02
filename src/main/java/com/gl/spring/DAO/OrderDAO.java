package com.gl.spring.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.gl.spring.entity.Order;
@Component
public interface OrderDAO {
	 
	Optional<Order> selectOrderById(String orderId);
	
	//public int insertOrder(Order order);
	
	//List<Order> selectOrderHistoryByMemberId(String memberId);
	
	

}
