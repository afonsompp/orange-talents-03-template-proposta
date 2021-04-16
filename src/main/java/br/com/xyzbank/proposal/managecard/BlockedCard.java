package br.com.xyzbank.proposal.managecard;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class BlockedCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String ipAddress;
	@Column(nullable = false)
	private String userAgent;
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Instant createdAt;

	@Deprecated
	public BlockedCard() {}

	public BlockedCard(String ipAddress, String userAgent) {
		this.ipAddress = ipAddress;
		this.userAgent = userAgent;
	}

	public Long getId() {
		return this.id;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public Instant getCreatedAt() {
		return this.createdAt;
	}

}
