package com.gl.spring.entity;

public enum ShippingType {
	SHOP("門市取貨",0),
	HOME("宅配",0),
	STORE("超商取貨",60);
	
	private final String SPName;
	private final double freight;
	
	private ShippingType(String SPName) {
		this(SPName,0);
	}
	
	ShippingType(String SPName, double freight) {
		this.SPName = SPName;
		this.freight = freight;		
	}

	public String getSPName() {
		return SPName;
	}
	public double getFreight() {
		return freight;
	}
	
	@Override
	public String toString() {
		return this.SPName + (freight<=0 ? "" : freight + "元");
	}
}
