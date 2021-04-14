package br.com.xyzbank.proposal.createproposal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String cardNumber;

	@Deprecated
	public Card() {

	}

	public Card(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getId() {
		return this.id;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

}
