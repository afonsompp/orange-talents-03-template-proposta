package br.com.xyzbank.proposal.shered.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Pattern.matches("[0-9]{5}-[0-9]{3}", value);
	}

}
