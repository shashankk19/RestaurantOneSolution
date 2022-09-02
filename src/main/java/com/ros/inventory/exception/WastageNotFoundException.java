package com.ros.inventory.exception;

public class WastageNotFoundException extends Exception {

	public WastageNotFoundException() {
		super();
	}

	public WastageNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WastageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public WastageNotFoundException(String message) {
		super(message);
	}

	public WastageNotFoundException(Throwable cause) {
		super(cause);
	}

}
