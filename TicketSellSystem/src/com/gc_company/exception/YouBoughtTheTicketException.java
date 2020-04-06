package com.gc_company.exception;

public class YouBoughtTheTicketException extends Exception {

	public YouBoughtTheTicketException() {
		super();
	}

	public YouBoughtTheTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public YouBoughtTheTicketException(String message, Throwable cause) {
		super(message, cause);
	}

	public YouBoughtTheTicketException(String message) {
		super(message);
	}

	public YouBoughtTheTicketException(Throwable cause) {
		super(cause);
	}

}
