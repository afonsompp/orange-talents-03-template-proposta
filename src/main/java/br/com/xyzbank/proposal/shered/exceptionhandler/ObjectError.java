package br.com.xyzbank.proposal.shered.exceptionhandler;

import java.time.Instant;

public class ObjectError {

	private String message;
	private Integer status;
	private Instant instant;
	private String error;

	public ObjectError(String message, Integer status, String error) {
		this.message = message;
		this.status = status;
		this.instant = Instant.now();
		this.error = error;
	}

	public String getMessage() {
		return this.message;
	}

	public Integer getStatus() {
		return this.status;
	}

	public Instant getInstant() {
		return this.instant;
	}

	public String getError() {
		return error;
	}

}
