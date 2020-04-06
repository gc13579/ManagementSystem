package com.gc_company.exception;

public class InsufficientTicketException extends Exception {

	public InsufficientTicketException() {
		super();
	}

	public InsufficientTicketException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InsufficientTicketException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientTicketException(String message) {
		super(message);
	}

	public InsufficientTicketException(Throwable cause) {
		super(cause);
	}

}
