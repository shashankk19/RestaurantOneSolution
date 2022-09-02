package com.ros.inventory.exception;

/**
 * No Supplier Found
 * 
 * @author kalyan.prathapaneni
 *
 */
public class SupplierNotFoundException extends Exception {

	public SupplierNotFoundException() {
		super();
	}

	public SupplierNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SupplierNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SupplierNotFoundException(String message) {
		super(message);
	}

	public SupplierNotFoundException(Throwable cause) {
		super(cause);
	}

}
