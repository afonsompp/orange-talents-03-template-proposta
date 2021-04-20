package br.com.xyzbank.proposal.managecard;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WalletRequest {

	@NotBlank
	@Email
	private String email;
	@NotNull
	private Wallet wallet;

	@Deprecated
	public WalletRequest() {}

	public WalletRequest(String email, Wallet wallet) {
		this.email = email;
		this.wallet = wallet;
	}

	public String getEmail() {
		return this.email;
	}

	public Wallet getWallet() {
		return this.wallet;
	}

	public WalletAssociationRequest toWalletFeignRequest() {
		return new WalletAssociationRequest(email, wallet.toString());
	}

	public WalletAssociation toWalletAssociation(String id, Card card) {
		return new WalletAssociation(email, wallet, id, card);
	}

}
