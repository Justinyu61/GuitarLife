package com.gl.spring.entity;

public class OrderItem {
	private Product product;
	private Color color;
	private String size;
	private String brand;
	private int quantity;
	private double price;
	
	private Product getProduct() {
		return product;
	}
	public Color getColor() {
		return color;
	}
	public String getSize() {
		return size;
	}
	public String getBrand() {
		return brand;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (brand == null) {
			if(other.brand != null)
				return false;
		}else if(!brand.equals(other.brand))
			return false;
		
		if(color == null) {
			if(other.color != null)
				return false;
		}else if(!color.equals(other.color))
			return false;
		
		if(product == null) {
			if(other.product != null)
				return false;
		}else if (!product.equals(other.product)) 
			return false;
		
		if(size == null) {
			if(other.size != null)
				return false;
		}else if(!size.equals(other.size))
			return false;
			
		
		return true;
	}
	
	@Override
	public String toString() {
		return "訂購明細 [訂購產品=" + product + ", 訂購顏色=" + color + ", 訂購尺寸=" + size + ", 訂購品牌=" + brand
				+ ", 訂購數量=" + quantity + ", 交易價格=" + price + "]";
	}

}
