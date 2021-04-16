package br.com.xyzbank.proposal.shered.exceptionhandler;

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

	private final String invalidData = "invalid.data.message";
	private final String apiException = "api.exception.message";

	public ValidationExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ObjectFieldErrors handler(MethodArgumentNotValidException exception) {
		List<FieldErrors> errors = extractFieldErrors(exception);

		String message = messageSource.getMessage(invalidData, null,
				LocaleContextHolder.getLocale());

		return new ObjectFieldErrors(message, HttpStatus.BAD_REQUEST.value(), errors);

	}

	@ExceptionHandler(ApiErrorException.class)
	public ResponseEntity<ObjectError> apiHandler(ApiErrorException e) {
		String message = messageSource.getMessage(apiException, null,
				LocaleContextHolder.getLocale());

		return ResponseEntity.status(e.getHttpStatus())
				.body(new ObjectError(message, e.getHttpStatus().value(), e.getReason()));

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
