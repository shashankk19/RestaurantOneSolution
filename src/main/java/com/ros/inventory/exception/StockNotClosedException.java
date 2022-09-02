package com.ros.inventory.exception;

public class StockNotClosedException extends Exception {

	public StockNotClosedException() {
		super();
	}

	public StockNotClosedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StockNotClosedException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockNotClosedException(String message) {
		super(message);
	}

	public StockNotClosedException(Throwable cause) {
		super(cause);
	}

}
