package br.com.xyzbank.proposal.shered.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import br.com.xyzbank.proposal.createproposal.AddressRequest;

public class ZipCodeValidatorTest {

	private Validator validator;

	@BeforeEach
	private void setUpEach() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@DisplayName("Shold verify if zip code is valid")
	@ParameterizedTest
	@MethodSource("provideParams")
	public void test1(AddressRequest address, Boolean expected) {
		var validation = validator.validate(address);

		assertEquals(expected, validation.isEmpty());
	}

	public static Stream<Arguments> provideParams() {

		return Stream.of(
				Arguments.of(new AddressRequest("street", "number", "76920-000", "city",
						"state", "country"), true),
				Arguments.of(new AddressRequest("street", "number", "76920000", "city",
						"state", "country"), false),
				Arguments.of(new AddressRequest("street", "number", "76920-o00", "city",
						"state", "country"), false));
	}
}
