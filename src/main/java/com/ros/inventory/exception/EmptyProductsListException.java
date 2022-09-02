package com.ros.inventory.exception;

public class EmptyProductsListException extends Exception {

	public EmptyProductsListException() {
		super();
	}

	public EmptyProductsListException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyProductsListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyProductsListException(String message) {
		super(message);
	}

	public EmptyProductsListException(Throwable cause) {
		super(cause);
	}

}
