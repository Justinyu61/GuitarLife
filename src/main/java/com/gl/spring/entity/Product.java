package com.gl.spring.entity;

import java.time.LocalDate;

public class Product {
	private int id;
	private String name;
	private String brand;
	private String description = "";
	private String photoUrl;
	private String type;
	private double unitPrice;
	private int stock;
	private LocalDate shelfDate = LocalDate.now();

	public Product() {
	}

	public Product(int id, String name, double unitPrice, int stock) {
		super();
		this.setId(id);
		this.setName(name);
		this.setUnitPrice(unitPrice);
		this.setStock(stock);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getDescription() {
		return description;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public String getType() {
		return type;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public int getStock() {
		return stock;
	}

	public LocalDate getShelfDate() {
		return shelfDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setShelfDate(LocalDate shelfDate) {
		this.shelfDate = shelfDate;
	}
	
	@Override
    public String toString() {
	return getClass().getName()
			+"產品代號="+ id
			+",\n 產品名稱="+ name
			+",\n 定價="+unitPrice
			+",\n 庫存="+stock
			+",\n 說明="+description
			+",\n 圖片網址="+photoUrl
			+",\n 類型="+type
	        +"]";
}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
