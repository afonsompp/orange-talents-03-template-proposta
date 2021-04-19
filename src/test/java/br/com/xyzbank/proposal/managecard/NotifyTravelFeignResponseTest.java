package br.com.xyzbank.proposal.managecard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NotifyTravelFeignResponseTest {

	@DisplayName("Should return true if value is CRIADO")
	@ParameterizedTest
	@CsvSource({
			"CRIAD, false",
			", false",
			"CRIADO, true"
	})
	public void name(String value, boolean expected) {
		var travel = new NotifyTravelFeignResponse(value);

		assertEquals(expected, travel.isNotified());
	}
}
