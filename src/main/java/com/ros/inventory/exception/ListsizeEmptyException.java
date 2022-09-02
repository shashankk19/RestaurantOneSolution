package com.ros.inventory.exception;

public class ListsizeEmptyException extends Exception {

	public ListsizeEmptyException() {
		super();
	}

	public ListsizeEmptyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ListsizeEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ListsizeEmptyException(String message) {
		super(message);
	}

	public ListsizeEmptyException(Throwable cause) {
		super(cause);
	}

}
