package com.ros.inventory.exception;

public class SiteTransferNotFoundException extends Exception {

	public SiteTransferNotFoundException() {
		super();
	}

	public SiteTransferNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SiteTransferNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SiteTransferNotFoundException(String message) {
		super(message);
	}

	public SiteTransferNotFoundException(Throwable cause) {
		super(cause);
	}

}
