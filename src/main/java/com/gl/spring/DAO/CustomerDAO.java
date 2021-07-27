package com.gl.spring.DAO;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.gl.spring.entity.Customer;
@Component
public interface CustomerDAO {
	
	Optional<Customer>selectCustomersById(String id);
	
	public int insertCustomer(Customer customer);
	
	public void updateCustomer(Customer customer);

}
