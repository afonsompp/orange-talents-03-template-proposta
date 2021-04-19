package br.com.xyzbank.proposal.managecard;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BlockedCardResponse {

	private BlockedCardStatus resultado;

	public BlockedCardResponse() {}

	@JsonCreator
	public BlockedCardResponse(BlockedCardStatus resultado) {
		this.resultado = resultado;
	}

	public BlockedCardStatus getResultado() {
		return this.resultado;
	}

}
