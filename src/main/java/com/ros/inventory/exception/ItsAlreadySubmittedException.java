package com.ros.inventory.exception;

public class ItsAlreadySubmittedException extends Exception {

	public ItsAlreadySubmittedException() {
		super();
	}

	public ItsAlreadySubmittedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ItsAlreadySubmittedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItsAlreadySubmittedException(String message) {
		super(message);
	}

	public ItsAlreadySubmittedException(Throwable cause) {
		super(cause);
	}

	

}
