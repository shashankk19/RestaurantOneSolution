package com.ros.inventory.exception;

public class StockNotFoundException extends Exception {

	public StockNotFoundException() {
		super();
	}

	public StockNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StockNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockNotFoundException(String message) {
		super(message);
	}

	public StockNotFoundException(Throwable cause) {
		super(cause);
	}

}
