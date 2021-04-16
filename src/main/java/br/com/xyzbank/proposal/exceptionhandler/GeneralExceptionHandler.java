package br.com.xyzbank.proposal.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GeneralExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> responseStatusHandler(ResponseStatusException e) {
		return ResponseEntity.status(e.getStatus()).build();
	}
}
