package com.jwtapi.common;

import lombok.Data;

@Data
public class NoResultFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434625383286619641L;

	private final String message;

	public NoResultFoundException(String message) {
		this.message = message;
	}
}
