package br.com.xyzbank.proposal.exceptionhandler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

	private final MessageSource messageSource;

	private String invalidData = "invalid.data.message";

	public ValidationExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ObjectError handler(MethodArgumentNotValidException exception) {
		List<FieldErrors> errors = extractFieldErrors(exception);

		String message = messageSource.getMessage(
				invalidData, null,
				LocaleContextHolder.getLocale());

		return new ObjectError(message, HttpStatus.BAD_REQUEST.value(), errors);

	}

	@ExceptionHandler(ApiErrorException.class)
	public ResponseEntity<ObjectError> apiHandler(ApiErrorException e) {
		List<FieldErrors> response = new ArrayList<>();
		response.add(new FieldErrors(e.getField(), e.getReason()));
		String message = messageSource.getMessage(
				invalidData, null,
				LocaleContextHolder.getLocale());

		return ResponseEntity.status(
				HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new ObjectError(message, HttpStatus.UNPROCESSABLE_ENTITY.value(),
						response));

	}

	private List<FieldErrors> extractFieldErrors(
			MethodArgumentNotValidException exception) {
		List<FieldErrors> response = new ArrayList<>();
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();

		errors.forEach(e -> {
			var message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			var error = new FieldErrors(e.getField(), message);
			response.add(error);
		});

		return response;
	}
}
