package com.ros.inventory.exception;

public class EmptyInvoiceListException extends Exception {

	public EmptyInvoiceListException() {
		super();
	}

	public EmptyInvoiceListException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyInvoiceListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyInvoiceListException(String message) {
		super(message);
	}

	public EmptyInvoiceListException(Throwable cause) {
		super(cause);
	}

	
}
