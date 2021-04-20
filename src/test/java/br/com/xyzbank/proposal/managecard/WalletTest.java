package br.com.xyzbank.proposal.managecard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import br.com.xyzbank.proposal.shered.exceptionhandler.ApiErrorException;

public class WalletTest {

	@DisplayName("Should throw ApiErrorException when value is invalid")
	@ParameterizedTest
	@CsvSource({ ",", "paypa" })
	public void test1(String value) {

		assertThrows(ApiErrorException.class, () -> Wallet.fromString(value));
	}

	@DisplayName("Should return Wallet what corresponding the valid string")
	@Test
	public void test2() {

		assertEquals(Wallet.PAYPAL, Wallet.fromString("paypal"));
	}

}
