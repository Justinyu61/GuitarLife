package com.gl.spring.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.gl.spring.exception.DLDataInvalidateException;



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
	private List<Color> colorList= new ArrayList<>();

	public Product() {}

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

	public void getShelfDate(String dateString) {
		if(shelfDate != null) {
			try {
				setShelfDate(LocalDate.parse(dateString));
				return;
			}catch(DateTimeParseException e) {
				throw new DLDataInvalidateException("產品上架日期(" + dateString + ")格式不正確，須符合iso8601");
			}
		}
	}
	
	public List<Color> getColorList(){
		return new ArrayList<>(colorList);
	}
	
	public Color findColor(String colorName,String size) {
		Color color = null;
		if((this.getColorList()==null || this.getColorList().size()==0)
				&&(colorName!= null && colorName.length()>0)) {
			throw new DLDataInvalidateException("無顏色清單的產品("+id+ "),不可以指派顏色"+colorName);
		}else if((this.getColorList()!=null && this.getColorList().size()>0)
				&&(colorName==null || colorName.length()==0)) {
			throw new DLDataInvalidateException("有顏色清單的產品("+id+"),必須選擇顏色");
		}else if((this.getColorList()!=null && this.getColorList().size()>0)
			&&(colorName !=null && colorName.length()>0)) {
			for(int i=0;i<colorList.size();i++) {
				Color theColor = colorList.get(i);
				if(theColor.getColorName().equals(colorName)) {
					return theColor;
				}				
			}
			throw new DLDataInvalidateException("有顏色清單,顏色選擇錯誤:"+colorName);
		}else {
			//檢查尺寸
			if(size!=null && size.length()>0) 
				throw new DLDataInvalidateException("沒有顏色清單的產品，不得指派尺寸:" + size);	
		}				
		return color;
	}
	
	 public void addColor(Color color) {
     	colorList.add(color);
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
