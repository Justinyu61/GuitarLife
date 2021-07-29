package com.gl.spring.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


public class Order {

	public int id;
	public Customer member;
	public LocalDate createdDate = LocalDate.now();
	public LocalTime createdTime = LocalTime.now();
	public int status = 0;

	public PaymentType paymentType;
	public double paymentFee;
	public String paymentNote;

	public ShippingType shippingType;
	public double shippingFee;
	public String shippingNote;

	public String recipientName;
	public String recipientEmail;
	public String recipientPhone;
	public String shippingAddress;
	public double totalAmount;

	private Set<OrderItem> orderItemSet = new HashSet<>();

	public Set<OrderItem> getOrderItemSet() {
		return new HashSet<>(orderItemSet);
	}

	public double getTotalAmountWithFee() {
		return Math.round(getTotalAmount() + paymentFee + shippingFee);
	}

	public void addOrderItem(OrderItem item) {// 給OrdersDAO.查詢一筆訂單
		orderItemSet.add(item);
	}

	public void addOrderItem(ShoppingCart cart) {// 給CheckoutServle將購物車內容一筆一筆建立購物明細後加入訂單
		if (cart == null || cart.size() == 0)
			throw new IllegalArgumentException("建立訂單內容不可以是空的");

		// 讀出一筆一筆購物明細(來自於購物車,CartItem),建立成訂單明細(OrderItem),並加入OrderItemSet
		for (CartItem cartItem : cart.getCartItemSet()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setColor(cartItem.getColor());
			orderItem.setSize(cartItem.getSize());
			orderItem.setBrand(cartItem.getBrand());
			orderItem.setQuantity(cart.getQuantity(cartItem));
			orderItem.setPrice(cartItem.getProduct().getUnitPrice());
			orderItemSet.add(orderItem);
		}
	}

	public int getTotalQuantity() {
		int sum = 0;
		if (orderItemSet != null && orderItemSet.size() > 0) {
			for (OrderItem orderItem : orderItemSet) {
				sum = sum + orderItem.getQuantity();
			}
		}
		return sum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getMember() {
		return member;
	}

	public void setMember(Customer member) {
		this.member = member;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = LocalDate.parse(createdDate);
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = LocalTime.parse(createdTime);
	}

	public int getStatus() {
		return status;
	}

	public String getStatusString(int status) {
		if (status < Status.values().length) {
			return Status.values()[status].SPName;
		} else {
			return String.valueOf(status);
		}
	}

	public String getStatusString() {
		return getStatusString(status);
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = PaymentType.valueOf(paymentType);
	}

	public double getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(double paymentFee) {
		this.paymentFee = paymentFee;
	}

	public String getPaymentNote() {
		return paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public ShippingType getShippingType() {
		return shippingType;
	}

	public void setShippingType(ShippingType shippingType) {
		this.shippingType = shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = ShippingType.valueOf(shippingType);
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getShippingNote() {
		return shippingNote;
	}

	public void setShippingNote(String shippingNote) {
		this.shippingNote = shippingNote;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public double getTotalAmount() {
		double sum = 0;
		if (orderItemSet != null && orderItemSet.size() > 0) {
			for (OrderItem orderItem : orderItemSet) {
				sum = sum + orderItem.getPrice() * orderItem.getQuantity();
			}
		} else {
			sum = totalAmount;
		}
		return Math.round(sum);
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Order [" + "編號=" + id + ", 訂購人=" + member + ",\n 訂購日期時間=" + createdDate + " " + createdTime + ", 處理狀態="
				+ status + ",\n 付款方式=" + paymentType + ", 手續費=" + paymentFee + ", 付款紀錄=" + paymentNote + ",\n 貨運方式="
				+ shippingType + ", 物流費=" + shippingFee + ", 貨運紀錄=" + shippingNote + ",\n 收件人=" + recipientName
				+ ", 收件人Email=" + recipientEmail + ", 收件人電話=" + recipientPhone + ",\n 送貨地址=" + shippingAddress
				+ ",\n 訂單內容:\n[" + orderItemSet + "]\n" + "共" + this.getOrderItemSet().size() + ", "
				+ this.getTotalQuantity() + "件，總金額為:" + this.getTotalAmount() + "元]";
	}

	public enum Status {
		NEW("新訂單"), TRANSFERED("已轉帳"), PAID("已入帳"), SHIPPED("已出貨"), ARRIVED("已到貨"), CHECKED("已簽收"), COMPLETED("已完成"),
		CANCELED("已取消"), REJECTING("要求退貨"), REJECTED("已退貨"), REFUNDING("要求退款"), REFUNDED("已退款"), FINALLY("已結案");

		private final String SPName;

		private Status(String TWName) {
			this.SPName = TWName;
		}

		public String getDescription() {
			return SPName;
		}
	}

}
