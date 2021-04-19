package br.com.xyzbank.proposal.managecard;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BlockedCardRequest {

	private String sistemaResponsavel;

	public BlockedCardRequest() {}

	@JsonCreator
	public BlockedCardRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return this.sistemaResponsavel;
	}

}
