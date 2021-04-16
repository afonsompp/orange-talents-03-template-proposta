package br.com.xyzbank.proposal.shered.exceptionhandler;

import org.springframework.http.HttpStatus;

public class ApiErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;

	private final String reason;
	private String field;

	public ApiErrorException(HttpStatus httpStatus, String reason) {
		super(reason);
		this.httpStatus = httpStatus;
		this.reason = reason;
	}

	public ApiErrorException(HttpStatus httpStatus, String reason, String field) {
		super(reason);
		this.httpStatus = httpStatus;
		this.reason = reason;
		this.field = field;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getField() {
		return field;
	}

	public String getReason() {
		return reason;
	}
}
