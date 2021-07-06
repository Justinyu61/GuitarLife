package com.gl.spring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.stereotype.Component;

import com.gl.spring.exception.DLException;
import com.gl.spring.model.Customer;
import com.gl.spring.model.VIP;


@Component 
class CustomerDAO {
	
	
	private static final String SELECT_CUSTOMERS_BY_ID= "SELECT id, email, password, name, gender, birthday, "
			+ "phone, address, subscribed, discount "
			+ "FROM customers "
			+ "WHERE id=? OR email=?";
	
	Customer selectCustomerById(String id)throws DLException{
Customer c = null;
		
		Connection connection =RDBConnection.getConnection();//1,2取得連線
		try(PreparedStatement pstmt = connection.prepareStatement(SELECT_CUSTOMERS_BY_ID);//3.準備指令
				) {
			//3.1傳入?的值
			pstmt.setString(1, id);//在資料庫裡0已經代表了別的東西,所以用1做傳入值
			pstmt.setString(2, id);
			//4執行指令
			try(
			ResultSet rs =pstmt.executeQuery();
			){
				//5處理rs,
				while(rs.next()) {
					int discount = rs.getInt("discount");
					if(discount>0) {
						c = new VIP();
						((VIP)c).setDiscount(discount);						
					}else {
						c = new Customer();
					}
					c = new Customer();
					c.setId(rs.getString("id"));
					c.setEmail(rs.getString("email"));
					c.setPassword(rs.getString("password"));
					c.setName(rs.getString("name"));
					c.setGender(rs.getString("gender").charAt(0));
					c.setBirthday(rs.getString("birthday"));
					c.setPhone(rs.getString("phone"));
					c.setAddress(rs.getString("address"));
					c.setSubscribed(rs.getBoolean("subscribed"));
					
				}
			}			
		} catch (SQLException e) {
			throw new DLException("用帳號查詢客戶資料失敗", e);			
		}
		
		   return c;	
		}
	
	private static final String INSERT_CUSTOMER = 
			"INSERT INTO customers"
			+ "(id, email, password, name, gender, birthday, phone, address, subscribed,discount)"
			+ "value(?,?,?,?,?,?,?,?,?,?)";
//	        + "(id, email, password, name, gender, birthday, phone, address, subscribed)"
//	        + "value(?,?,?,?,?,?,?,?,?)";

	 void insert(Customer customer) throws DLException{
				 //TODO: update
		 try (
			 Connection connection = RDBConnection.getConnection();//1.2 取得連線
			 PreparedStatement pstmt = connection.prepareStatement(INSERT_CUSTOMER);//3.準備指令
		){
			//3.1傳入?的值
			 pstmt.setString(1, customer.getId());
			 pstmt.setString(2, customer.getEmail());
			 pstmt.setString(3, customer.getPassword());
			 pstmt.setString(4, customer.getName());
			 pstmt.setString(5, String.valueOf(customer.getGender()));
			 pstmt.setString(6, String.valueOf(customer.getBirthday()));
			 pstmt.setString(7, customer.getPhone());
			 pstmt.setString(8, customer.getAddress());
			 pstmt.setBoolean(9, customer.isSubscribed());
			 if(customer instanceof VIP) {
				 pstmt.setInt(10, ((VIP)customer).getDiscount());
			 }else {
				 pstmt.setInt(10, 0);
			 }
			
			 
			 
			 
			 pstmt.executeUpdate();//4.執行指令
			 }catch (SQLIntegrityConstraintViolationException e) {
			     if(e.getMessage().indexOf("email")>0) {
			    	 throw new DLException("E-mail已重複使用", e);
			     }else if(e.getMessage().indexOf("PRIMARY")>0) {
			    	 throw new DLException("ID("+customer.getId()+")已重複使用", e);
			     }
			     throw new DLException("新增客戶資料失敗", e);
		     } catch (SQLException e) {
			   throw new DLException("新增客戶資料失敗", e);
		     }
	 }
	 private static final String UPDATE_CUSTOMER = 
				"UPDATE INTO customers"
				+ "SET email=? password=? name=? gender=? birthday=? phone=? address=? subscribed=? discount=?"
				+ "WHERE id=?";


		 void update(Customer customer) throws DLException{
					 //TODO: update
			 try (
				 Connection connection = RDBConnection.getConnection();//1.2 取得連線
				 PreparedStatement pstmt = connection.prepareStatement(UPDATE_CUSTOMER);//3.準備指令
			){
				//3.1傳入?的值
				 pstmt.setString(10, customer.getId());
				 pstmt.setString(1, customer.getEmail());
				 pstmt.setString(2, customer.getPassword());
				 pstmt.setString(3, customer.getName());
				 pstmt.setString(4, String.valueOf(customer.getGender()));
				 pstmt.setString(5, String.valueOf(customer.getBirthday()));
				 pstmt.setString(5, customer.getPhone());
				 pstmt.setString(7, customer.getAddress());
				 pstmt.setBoolean(8, customer.isSubscribed());
				 if(customer instanceof VIP) {
					 pstmt.setInt(9, ((VIP)customer).getDiscount());
				 }else {
					 pstmt.setInt(10, 0);
				 }
				
				 
				 
				 
				 pstmt.executeUpdate();//4.執行指令
				 }catch (SQLIntegrityConstraintViolationException e) {
				     if(e.getMessage().indexOf("email")>0) {
				    	 throw new DLException("E-mail已重複使用", e);
				     }else if(e.getMessage().indexOf("PRIMARY")>0) {
				    	 throw new DLException("ID("+customer.getId()+")已重複使用", e);
				     }
				     throw new DLException("新增客戶資料失敗", e);
			     } catch (SQLException e) {
				   throw new DLException("新增客戶資料失敗", e);
			     }
	}

}
