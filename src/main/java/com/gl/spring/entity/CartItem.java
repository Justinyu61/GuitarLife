package com.gl.spring.entity;


public class CartItem {
	private Product product;
	private Color color;
	private String brand;
	private String size = "";
	
	
	public Product getProduct() {
		System.out.println("123");
		return product;
	}
	public Color getColor() {
		return color;
	}
	public String getBrand() {
		return brand;
	}
	public String getSize() {
		return size;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}	
	public void setColor(Color color) {
		this.color = color;
	}	
	public void setBrand(String brand) {
		this.brand = brand;
	}	
	public void setSize(String size) {
		if(size == null) size = "";
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "產品=" + product + "顏色=" + color + "品牌"+brand+"尺寸=" + size ;
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
	

}
