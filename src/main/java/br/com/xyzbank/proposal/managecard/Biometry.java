package br.com.xyzbank.proposal.managecard;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Biometry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String biometry;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Card card;
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Instant createdAt;

	@Deprecated
	public Biometry() {}

	public Biometry(String biometry, Card card) {
		this.biometry = biometry;
		this.card = card;
	}

	public Long getId() {
		return id;
	}

	public String getBiometry() {
		return this.biometry;
	}

	public Card getCard() {
		return this.card;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

}
