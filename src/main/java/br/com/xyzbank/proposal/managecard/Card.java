package br.com.xyzbank.proposal.managecard;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String cardNumber;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(unique = true)
	private BlockedCard blocked;

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

	public BlockedCard getBlocked() {
		return blocked;
	}

	public void setBlocked(BlockedCard blocked) {
		this.blocked = blocked;
	}

}
