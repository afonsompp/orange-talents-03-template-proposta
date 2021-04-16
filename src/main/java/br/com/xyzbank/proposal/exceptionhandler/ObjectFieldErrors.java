package br.com.xyzbank.proposal.exceptionhandler;

import java.time.Instant;
import java.util.List;

public class ObjectFieldErrors {

	private String message;
	private Integer status;
	private Instant instant;
	private List<FieldErrors> errors;

	public ObjectFieldErrors(String message, Integer status, List<FieldErrors> errors) {
		this.message = message;
		this.status = status;
		this.instant = Instant.now();
		this.errors = errors;
	}

	public ObjectFieldErrors() {}

	public String getMessage() {
		return this.message;
	}

	public Integer getStatus() {
		return this.status;
	}

	public Instant getInstant() {
		return this.instant;
	}

	public List<FieldErrors> getErrors() {
		return errors;
	}
}
