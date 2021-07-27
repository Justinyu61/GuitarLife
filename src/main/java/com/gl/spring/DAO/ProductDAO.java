package com.gl.spring.DAO;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gl.spring.entity.Product;




@Component
public interface ProductDAO {
	
	public List<Product> selectAllProducts(); 
//	{
//	List<Product> list = dao.selectAllProducts();
//	return list;
//	}
//	public List<Product> selectProductsByBrandAndType(String brand, String type){
//		List<Product> list = dao.selectProductsByBrandAndType(brand, type);
//		return list;
//	}
//	public List<String> selectAllDistinctBrand(){
//		List<String> list = dao.selectAllDistinctBrand();
//		return list;
//	}
//	public List<String> selectAllDistinctType(){
//		List<String> list = dao.selectAllDistinctType();
//		return list;
//	}
//	public List<Product> selectProductsByBrand(String brand){
//		List<Product> list = dao.selectProductsByBrand(brand);
//		return list;
//	}
//	Product selectProductsById(String id) {		
//		return p;
//	}

}
