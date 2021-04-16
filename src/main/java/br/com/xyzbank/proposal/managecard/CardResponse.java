package br.com.xyzbank.proposal.managecard;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CardResponse {

	private String id;

	public CardResponse() {}

	@JsonCreator
	public CardResponse(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public Card toCard() {
		return new Card(id);
	}

}
