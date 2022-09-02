package com.ros.inventory.exception;

public class StockEmptyException extends Exception {

	public StockEmptyException() {
		super();
	}

	public StockEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StockEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockEmptyException(String message) {
		super(message);
	}

	public StockEmptyException(Throwable cause) {
		super(cause);
	}

}
