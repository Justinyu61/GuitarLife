package com.gl.spring.entity;

public enum PaymentType {
	
	SHOP("門市取貨",ShippingType.SHOP),
	ATM("ATM轉帳",ShippingType.HOME, ShippingType.STORE),
	HOME("貨到付款",0,ShippingType.HOME),
	STORE("超商取貨付款",ShippingType.STORE),
	CARD("信用卡付款",ShippingType.HOME, ShippingType.STORE);
	
	private final String SPName;
	private final double freight;
	private final ShippingType[] shippingTypeArrays;
	
	private PaymentType(String sPName,ShippingType...shippingTypeArrays) {
		this(sPName,0,shippingTypeArrays);
	}
	
	private PaymentType(String sPName,double freight,ShippingType...shippingTypeArrays) {
		this.SPName = sPName;
		this.freight = freight;
		this.shippingTypeArrays = shippingTypeArrays;		
	}
	
	public String getSPName() {
		return SPName;
	}
	public double getFreight() {
		return freight;
	}
	public ShippingType[] getShippingTypeArrays() {
		return shippingTypeArrays.clone();
	}
	public String  getShippingType(){
		String data = "";
		for(ShippingType shType:shippingTypeArrays) {
			if(data.length()>0) data+=",";
			data+=shType.name();
		}
		return data;
	}
	
	public String toString() {
		return this.SPName + (freight<=0?"":freight+"元");
	}
	

}
