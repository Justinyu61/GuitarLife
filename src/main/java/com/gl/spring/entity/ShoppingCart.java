package com.gl.spring.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ShoppingCart {
	private Customer member;
	private Map<CartItem , Integer> cartMap = new HashMap<>();
	
	
	public Customer getMember() {
		return member;
	}
	public void setMember(Customer member) {
		this.member = member;
	}
	
	public int size() {
		return cartMap.size();
	}
	
	public boolean isEmpty() {
		return cartMap.isEmpty();
	}
	
	public Integer getQuantity(CartItem item) {
		return cartMap.get(item);
	}
	
	public Set<CartItem> getCartItemSet(){
		return cartMap.keySet();
	}
	
	public int getTotalQuantity() {
		int sum = 0;
		for(Integer qty:cartMap.values()) {
			if(qty != null) {
				sum = sum + qty;
			}
		}
		return sum;
	}
	
	public double getTotalAmount() {
		double sum = 0;
		for(Entry<CartItem,Integer> entry:cartMap.entrySet()) {
			CartItem item = entry.getKey();
			Integer qty = entry.getValue();
			sum = sum + (item.getProduct().getUnitPrice()*qty);
		}
		return Math.round(sum);
	}
	
	public void addToCart(Product p , String colorName,String size , int quantity) {
		if( p == null) throw new IllegalArgumentException("加入購物車時產品不得為null");
		Color color = p.findColor(colorName, size);
		CartItem item = new CartItem();
		item.setProduct(p);
		item.setColor(color);
		item.setSize(size);
		
		Integer oldQty = cartMap.get(item);
		if(oldQty != null)
			quantity = quantity + oldQty;
		
		cartMap.put(item, quantity);
	}
	
	public Integer remove(CartItem item) {
		return cartMap.remove(item);
	}
	
	public void updateToCart(CartItem item , int quantity) {
		Integer oldQty = cartMap.get(item);
		if(oldQty != null) {
			cartMap.put(item, quantity);
		}
	}
	
	@Override
	public String toString() {
			return "購物車 [訂購人=" + member 
					+ ",\n 購物明細=" + cartMap 
					+ ",\n 共" + size() + "項, " 
						+ getTotalQuantity() + "件, 總金額:" + getTotalAmount()
					+ "]";
		}
}
