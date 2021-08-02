package com.gl.spring.exception;

import com.gl.spring.entity.OrderItem;

public class StockShortageException extends DLException {
	private static final long serialVersionUID = 1L;
	
	private OrderItem item;

	public StockShortageException() {}

	public StockShortageException(String message, Throwable cause, OrderItem item) {
		super(message,cause);
		this.item = item;
	}

	public StockShortageException(String message, OrderItem item) {
		super(message);
		this.item = item;
	}

	@Override
	public String toString() {
		return "庫存不足: " + item;
	}
}
