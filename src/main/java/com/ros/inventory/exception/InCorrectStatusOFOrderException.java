package com.ros.inventory.exception;

public class InCorrectStatusOFOrderException extends Exception {

	public InCorrectStatusOFOrderException() {
		super();
	}

	public InCorrectStatusOFOrderException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InCorrectStatusOFOrderException(String message, Throwable cause) {
		super(message, cause);
	}

	public InCorrectStatusOFOrderException(String message) {
		super(message);
	}

	public InCorrectStatusOFOrderException(Throwable cause) {
		super(cause);
	}

}
