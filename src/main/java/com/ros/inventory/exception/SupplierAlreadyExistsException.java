package com.ros.inventory.exception;

/**
 * Supplier Already exists	
 * @author kalyan.prathapaneni
 *
 */
public class SupplierAlreadyExistsException extends Exception {

	public SupplierAlreadyExistsException() {
		super();
	}

	public SupplierAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SupplierAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public SupplierAlreadyExistsException(String message) {
		super(message);
	}

	public SupplierAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
