package com.gl.spring.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gl.spring.exception.DLException;



class RDBConnection {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/GuitarLife?serverTimezone=CST";
	private static final String user = "root";
	private static final String password = "ji3cl3gj94";
	
	static {
		//TODO: 讀取設定
		
	}
	static Connection getConnection() throws DLException{
	
	try {
		Class.forName(driver);//1.載入Driver
		
		try{
			Connection connection= DriverManager.getConnection(url,user,password);//2.建立連線
			return connection;	
		}catch(SQLException e) {
				throw new DLException("用帳號查詢客戶資料失敗",e);//底層資料庫出錯 ,e(逗號e)代表回傳此問題給管理人員
				}
		}catch(ClassNotFoundException e) {
			throw new DLException("無法載入jdbc Driver"+driver);
			}	
	
	}
}


