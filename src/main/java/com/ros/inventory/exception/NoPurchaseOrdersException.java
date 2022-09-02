package com.ros.inventory.exception;

public class NoPurchaseOrdersException extends Exception {

	public NoPurchaseOrdersException() {
		super();
	}

	public NoPurchaseOrdersException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoPurchaseOrdersException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoPurchaseOrdersException(String message) {
		super(message);
	}

	public NoPurchaseOrdersException(Throwable cause) {
		super(cause);
	}

}
