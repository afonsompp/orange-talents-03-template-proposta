package br.com.xyzbank.proposal.createproposal;

import java.math.BigDecimal;

public class ProposalResponse {

	private Long id;
	private String name;
	private String email;
	private String idCard;
	private BigDecimal salary;
	private AddressRequest address;

	@Deprecated
	public ProposalResponse() {

	}

	public ProposalResponse(Proposal proposal) {
		this.id = proposal.getId();
		this.name = proposal.getName();
		this.email = proposal.getEmail();
		this.idCard = proposal.getIdCard();
		this.salary = proposal.getSalary();
		this.address = new AddressRequest(proposal.getStreet(), proposal.getNumber(),
				proposal.getZipCode(), proposal.getCity(), proposal.getState(),
				proposal.getCountry());
	}

	public Long getId() {
		return id;
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

	public AddressRequest getAddress() {
		return this.address;
	}

}
