package com.ros.inventory.exception;

public class ClosedStockNotfoundException extends Exception {

	public ClosedStockNotfoundException() {
		super();
	}

	public ClosedStockNotfoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ClosedStockNotfoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClosedStockNotfoundException(String message) {
		super(message);
	}

	public ClosedStockNotfoundException(Throwable cause) {
		super(cause);
	}
	

}
