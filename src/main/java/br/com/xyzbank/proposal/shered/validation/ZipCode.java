package br.com.xyzbank.proposal.shered.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ZipCodeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipCode {
	String message() default "{constraint.zipcode}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
