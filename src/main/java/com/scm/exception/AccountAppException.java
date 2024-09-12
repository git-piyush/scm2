package com.scm.exception;

import org.springframework.http.HttpStatus;

public class AccountAppException extends RuntimeException {

	private HttpStatus status;

	private String message;

	public AccountAppException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public AccountAppException(String message) {
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
