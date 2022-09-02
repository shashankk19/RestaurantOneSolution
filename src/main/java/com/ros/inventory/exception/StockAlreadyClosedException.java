package com.ros.inventory.exception;

public class StockAlreadyClosedException extends Exception {

	public StockAlreadyClosedException() {
		super();
	}

	public StockAlreadyClosedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StockAlreadyClosedException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockAlreadyClosedException(String message) {
		super(message);
	}

	public StockAlreadyClosedException(Throwable cause) {
		super(cause);
	}

}
