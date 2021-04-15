package br.com.xyzbank.proposal.createproposal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

class BiometryInBase64ValidatorTest {

	@DisplayName("Should validate if field is a valid base64 string")
	@ParameterizedTest
	@MethodSource("provideValues")
	void test1(Object target, Errors errors, Boolean expected) {
		var validator = new BiometryInBase64Validator();

		validator.validate(target, errors);
		assertEquals(expected, errors.hasFieldErrors("biometry"));
	}

	private static Stream<Arguments> provideValues() {
		Object targetValid = new BiometryRequest("aQ==");
		Object targetInvalid = new BiometryRequest("AQ=");

		Errors errors = new BeanPropertyBindingResult(targetInvalid, "test");
		errors.rejectValue(null, null);

		return Stream.of(
				Arguments.of(targetValid,
						new BeanPropertyBindingResult(targetValid, "test"), false),
				Arguments.of(targetInvalid,
						new BeanPropertyBindingResult(targetValid, "test"), true),
				Arguments.of(targetInvalid, errors, false));
	}

}
