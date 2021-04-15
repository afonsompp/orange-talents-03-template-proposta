package br.com.xyzbank.proposal.createproposal;

public class BiometryResponse {

	private Long id;
	private String biometry;
	private CardResponse card;

	public BiometryResponse(Biometry bio) {
		this.id = bio.getId();
		this.biometry = bio.getBiometry();
		this.card = new CardResponse(bio.getCard().getCardNumber());
	}

	public Long getId() {
		return this.id;
	}

	public String getBiometry() {
		return this.biometry;
	}

	public CardResponse getCard() {
		return this.card;
	}

}
