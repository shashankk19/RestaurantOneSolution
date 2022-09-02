package com.ros.inventory.exception;

public class InvoiceNotFoundException extends Exception {

	public InvoiceNotFoundException() {
		super();
	}

	public InvoiceNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvoiceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvoiceNotFoundException(String message) {
		super(message);
	}

	public InvoiceNotFoundException(Throwable cause) {
		super(cause);
	}

}
