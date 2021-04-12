package br.com.xyzbank.proposal.createproposal;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proposal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
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

	@Deprecated
	public Proposal() {

	}

	public Proposal(String name, String email, String idCard, BigDecimal salary,
			AddressRequest address) {
		this.name = name;
		this.email = email;
		this.idCard = idCard;
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

}
