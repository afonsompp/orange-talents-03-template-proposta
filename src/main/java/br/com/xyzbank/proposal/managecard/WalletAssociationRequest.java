package br.com.xyzbank.proposal.managecard;

public class WalletAssociationRequest {
	private String email;
	private String carteira;

	@Deprecated
	public WalletAssociationRequest() {}

	public WalletAssociationRequest(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return this.email;
	}

	public String getCarteira() {
		return this.carteira;
	}

}
