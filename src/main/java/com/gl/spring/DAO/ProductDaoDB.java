package com.gl.spring.DAO;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.gl.spring.entity.Product;



@Component
public class ProductDaoDB implements ProductDAO{
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String SELECT_ALL_PRODUCTS = "SELECT id , name, brand,unit_price, "
			+ "stock, description, photo_url,discount " + "FROM products";
//	final  String SELECT_PRODUCTS_BY_BRAND_AND_TYPE = "SELECT id, name, brand, shelf_date, "
//			+ "unit_price, stock, description,photo_url, discount,type " + "FROM products "
//			+ "WHERE brand LIKE ? AND type LIKE ?";
//	final  String SELECT_ALL_Distinct_Brand = "SELECT DISTINCT brand FROM products";
//	final  String SELECT_ALL_Distinct_Type = "SELECT DISTINCT type FROM products ";
//	final  String SELECE_All_PRODUCTS_BY_BRAND = "SELECT name,brand,unit_price,stock "	 
//			+"FROM products "
//			+"where brand = ?";
//	final  String SELECT_PRODUCTS_BY_BRAND="SELECT id,name,brand,photo_url,discount,unit_price "	 
//			+"FROM products "
//			+"where brand like ?";
//	final  String SELECT_PRODUCTS_BY_ID = "SELECT id, name, brand, unit_price, stock, description, photo_url,discount,shelf_date "
//     		+ "FROM products "
//     		+ "WHERE id = ?";
	
	private RowMapper<Product> ptMapper = new RowMapper<Product>() {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product p = new Product();
			p.setId(rs.getInt("Id"));
			p.setName(rs.getString("name"));
			p.setBrand(rs.getString("brand"));
			p.setUnitPrice(rs.getDouble("unit_price"));
			p.setStock(rs.getInt("stock"));
			p.setDescription(rs.getString("description"));
			p.setPhotoUrl(rs.getString("photo_url"));
						
			return p;
		}
	};
	
	@Override
	public  List<Product> selectAllProducts(){
		List<Product> list = new ArrayList<Product>();		
		return jdbcTemplate.query(SELECT_ALL_PRODUCTS, ptMapper);
	}


}
