package br.com.xyzbank.proposal.createproposal;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BiometryInBase64Validator implements Validator {

	@Value("${biometry.validator.message}")
	private String message;

	@Override
	public boolean supports(Class<?> clazz) {
		return BiometryRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors())
			return;

		var biometry = (BiometryRequest) target;

		try {
			Base64.getDecoder().decode(biometry.getBiometry());
		} catch (IllegalArgumentException e) {
			errors.rejectValue("biometry", null, message);
		}
	}

}
