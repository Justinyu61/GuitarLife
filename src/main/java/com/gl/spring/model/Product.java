package com.gl.spring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gl.spring.exception.DLDataInvalidateException;


@Component
public class Product {

	private int id;//Pkey,required,alto-increment
	private String name;//unique-index,required,2~50
	private String brand;				
	private double unitPrice;//定價就是售價
	private int stock;
	private String  description="";
	private String photoUrl;
	private String type;
	private LocalDate shelfDate=LocalDate.now();
	private List<Color> colorList= new ArrayList<>(); //餓漢模式
		
	
//	private List<Color> colorList=new ArrayList<>();
	
	public Product() { } 
	//測試用
//	public Product(Product anotherProduct){
//		this.setId(anotherProduct.getId());
//		this.setName(anotherProduct.getName());
//		this.setBrand(anotherProduct.getBrand());
//		this.setUnitPrice(anotherProduct.getUnitPrice());
//	    this.setStock(anotherProduct.getStock());
//		this.setDescription(anotherProduct.getDescription());
//		this.setPhotoUrl(anotherProduct.getPhotoUrl());
//	}
	public Product(int id, String name, double unitPrice){ 
		super();
		this.setId(id);
		this.setName(name);
		this.setUnitPrice(unitPrice);
	}
	
	public Product(int id, String name,double unitPrice,int stock) {
		this(id, name, unitPrice);
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
	public double getUnitPrice() {//查詢售價(順便查定價)
		return unitPrice;
	}
	
	public int getStock() {
		return stock;
	}
	public String getDescription() {
		return description;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public LocalDate getShelfDate() {
		return shelfDate;
	}	
	public String getType() {
	   return type;
    }
	public void setShelfDate(String dateString) {//給DAO讀取一筆產品時，紀錄上架日期用的
		if(shelfDate!=null) {
			try {
				setShelfDate(LocalDate.parse(dateString));
				return;
			}catch(java.time.format.DateTimeParseException e) {
				throw new DLDataInvalidateException(
						"產品上架日期("+dateString+")格式不正確，須符合iso8601");
			}
		}
	}
	//集合(List, Set, Map)的getter(accesser(s))至少要用以下示範的
	public List<Color> getColorList() {
		//爛寫法
		//return colorList;
		
		//做法1:回傳不可變得顏色清單
//	return Collections.unmodifiableList(colorList) ;
	
	//做法2:回傳集合(顏色清單)的複本
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
	
	//集合(List, Set, Map)不可以直接寫setter,要改寫成mutator(s)
    public void addColor(Color color) {
    	colorList.add(color);
    }
		public void setShelfDate(LocalDate shelfDate) {
		this.shelfDate = shelfDate;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setId(int id) {
		this.id = id;			
	}
	
    public void setType(String type) {
	   this.type = type;
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
