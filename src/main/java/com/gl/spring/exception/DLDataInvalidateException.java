package com.gl.spring.exception;

public class DLDataInvalidateException extends RuntimeException {

	public DLDataInvalidateException() {
		super();
		// 這個無參數建構式會留著
	}

	public DLDataInvalidateException(String message, Throwable cause) {
		super(message, cause);
		// 這一條...可以隱掉沒差
	}

	public DLDataInvalidateException(String message) {
		super(message);
		// 基本上就是只用這個作錯誤處理
	}

	
	
}
