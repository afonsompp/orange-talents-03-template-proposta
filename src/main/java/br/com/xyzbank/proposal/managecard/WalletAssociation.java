package br.com.xyzbank.proposal.managecard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class WalletAssociation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Wallet wallet;
	@Column(nullable = false)
	private String associationId;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Card card;

	@Deprecated
	public WalletAssociation() {

	}

	public WalletAssociation(String email, Wallet wallet, String associationId,
			Card card) {
		this.email = email;
		this.wallet = wallet;
		this.associationId = associationId;
		this.card = card;
	}

	public Long getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public Wallet getWallet() {
		return this.wallet;
	}

	public String getAssociationId() {
		return this.associationId;
	}

	public Card getCard() {
		return this.card;
	}

}
