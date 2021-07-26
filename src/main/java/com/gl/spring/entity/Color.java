package com.gl.spring.entity;

public class Color {

	private String colorName;
	private String iconUrl;
	private String photoUrl;
	private int stock;

	public String getColorName() {
		return colorName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public int getStock() {
		return stock;
	}

	public void setColorName(String colorid) {
		this.colorName = colorid;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return "顏色=" + colorName + "小圖示=" + iconUrl + "圖示=" + photoUrl + "庫存=" + stock;
	}
	
}
