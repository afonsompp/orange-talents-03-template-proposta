package br.com.xyzbank.proposal.createproposal;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import br.com.xyzbank.proposal.managecard.Card;
import br.com.xyzbank.proposal.shered.util.BcryptEncoder;

@Entity
public class Proposal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false, unique = true)
	private String idCard;
	@Column(nullable = false)
	private BigDecimal salary;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private String number;
	@Column(nullable = false)
	private String zipCode;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private String country;

	@Enumerated(EnumType.STRING)
	private ProposalStatus status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private Card card;

	@Deprecated
	public Proposal() {

	}

	public Proposal(String name, String email, String idCard, BigDecimal salary,
			AddressRequest address) {
		this.name = name;
		this.email = email;
		this.idCard = BcryptEncoder.encodeIfPlainText(idCard);
		this.salary = salary;
		this.street = address.getStreet();
		this.number = address.getNumber();
		this.zipCode = address.getZipCode();
		this.city = address.getCity();
		this.state = address.getState();
		this.country = address.getCountry();
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public String getStreet() {
		return this.street;
	}

	public String getNumber() {
		return this.number;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public String getCountry() {
		return this.country;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public ProposalStatus getStatus() {
		return status;
	}

	public void setStatus(ProposalStatus status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Proposal)) {
			return false;
		}
		Proposal proposal = (Proposal) o;
		return id.equals(proposal.id) && idCard.equals(proposal.idCard);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idCard);
	}

}
