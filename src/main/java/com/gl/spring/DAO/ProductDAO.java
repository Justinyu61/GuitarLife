package com.gl.spring.DAO;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.gl.spring.entity.Product;

@Component
public interface ProductDAO {//interface只是宣告名稱 不會有方法的
	
	public List<Product> selectAllProducts(); 

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
	public List<Product> selectProductsByBrand(String brand);

	Optional<Product>selectProductsById(String id);		


}
