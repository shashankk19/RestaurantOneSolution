package com.ros.inventory.exception;

public class PurchaseOrderInvalidException extends Exception {

	public PurchaseOrderInvalidException() {
		super();
	}

	public PurchaseOrderInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PurchaseOrderInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public PurchaseOrderInvalidException(String message) {
		super(message);
	}

	public PurchaseOrderInvalidException(Throwable cause) {
		super(cause);
	}

}
