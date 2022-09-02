package com.ros.inventory.exception;

public class PurchaseOrderAlreadyExistsException extends Exception {

	public PurchaseOrderAlreadyExistsException() {
		super();
	}

	public PurchaseOrderAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PurchaseOrderAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public PurchaseOrderAlreadyExistsException(String message) {
		super(message);
	}

	public PurchaseOrderAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	
	
	
}
