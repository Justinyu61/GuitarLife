package com.gl.spring.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.gl.spring.entity.Customer;

@Component
public class CustomerDaoDB implements CustomerDAO {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	final String SELECT_CUSTOMERS_BY_ID = "SELECT id, email, password, name, gender, birthday, "
			+ "phone, address, subscribed, discount " + "FROM customers " + "WHERE id=? OR email=?";
	final String INSERT_CUSTOMER = "INSERT INTO customers"
			+ "(id, email, password, name, gender, birthday, phone, address, subscribed,discount)"
			+ "value(?,?,?,?,?,?,?,?,?,?)";
	final String UPDATE_CUSTOMER = "UPDATE INTO customers"
			+ "SET email=? password=? name=? gender=? birthday=? phone=? address=? subscribed=? discount=?"
			+ "WHERE id=?";

	private RowMapper<Customer> crMapper = new RowMapper<Customer>() {
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer c = new Customer();
			c.setId(rs.getString("id"));
			c.setEmail(rs.getString("email"));
			c.setPassword(rs.getString("password"));
			c.setName(rs.getString("name"));
			c.setGender(rs.getString("gender").charAt(0));
			c.setBirthday(rs.getString("birthday"));
			c.setPhone(rs.getString("phone"));
			c.setAddress(rs.getString("address"));
			c.setSubscribed(rs.getBoolean("subscribed"));
			return c;
		}
	};

	@Override
	public Optional<Customer> selectCustomerById(String id) {
		List<Customer> result = jdbcTemplate.query(SELECT_CUSTOMERS_BY_ID, crMapper, id ,id);
		if (result.isEmpty()) { 
			return Optional.empty();
		} else {
			return Optional.of(result.get(0));// Optional類的of()方法用於獲取具有指定類型的指定值的Optiomal類的實例
		}
	}

	@Override
	public int insertCustomer(Customer customer) {
		logger.log(Level.INFO, "registerDAO");
		return jdbcTemplate.update(INSERT_CUSTOMER, customer.getId(), customer.getEmail(), customer.getPassword(),
				customer.getName(), String.valueOf(customer.getGender()), String.valueOf(customer.getBirthday()),
				customer.getPhone(), customer.getAddress(), customer.isSubscribed(),customer.getDiscount());// 
	}

	@Override
	public void updateCustomer(Customer customer) {
		int upCus = jdbcTemplate.update(UPDATE_CUSTOMER, customer.getEmail(), customer.getPassword(),
				customer.getName(), String.valueOf(customer.getGender()), String.valueOf(customer.getBirthday()),
				customer.getPhone(), customer.getAddress(), customer.isSubscribed(),customer.getDiscount(), customer.getId());// 
		if (upCus != 0) {
			System.out.println("客戶資料更新成功:" + customer.getId());
		} else {
			System.out.println("客戶資料更新失敗:" + customer.getId());
		}
	}
}
