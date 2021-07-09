package com.gl.spring.model;

import org.springframework.stereotype.Component;

@Component
public class Color {
	

	private String colorName;
	private String iconUrl;
	private String photoUrl;
	private int stock;

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorid) {
		this.colorName = colorid;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "顏色=" + colorName + "小圖示=" + iconUrl + "圖示=" + photoUrl + "庫存=" + stock;
	}
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colorName == null) ? 0 : colorName.hashCode());
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
		Color other = (Color) obj;
		if (colorName == null) {
			if (other.colorName != null)
				return false;
		} else if (!colorName.equals(other.colorName))
			return false;
		return true;
	}
}

