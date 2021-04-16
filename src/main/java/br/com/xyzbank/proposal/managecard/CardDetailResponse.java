package br.com.xyzbank.proposal.managecard;

public class CardDetailResponse {

	private Long id;
	private String cardNumber;

	public CardDetailResponse() {}

	public CardDetailResponse(Card card) {
		this.id = card.getId();
		this.cardNumber = card.getCardNumber();
	}

	public Long getId() {
		return this.id;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

}
