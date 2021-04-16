package br.com.xyzbank.proposal.managecard;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonCreator;

public class BiometryRequest {

	@NotBlank
	private String biometry;

	@Deprecated
	public BiometryRequest() {

	}

	@JsonCreator
	public BiometryRequest(String biometry) {
		this.biometry = biometry;
	}

	public String getBiometry() {
		return this.biometry;
	}

	public Biometry toBiometry(Card card) {
		return new Biometry(biometry, card);
	}

}
