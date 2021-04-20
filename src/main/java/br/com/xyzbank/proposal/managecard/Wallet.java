package br.com.xyzbank.proposal.managecard;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import br.com.xyzbank.proposal.shered.exceptionhandler.ApiErrorException;

public enum Wallet {
	PAYPAL,
	SAMSUNG_PAY;

	@JsonCreator
	public static Wallet fromString(String value) {
		if (value == null)
			throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Invalid value");
		for (var wallet : values()) {
			if (value.equalsIgnoreCase(wallet.toString())) {
				return wallet;
			}
		}
		throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Invalid value");
	}
}
