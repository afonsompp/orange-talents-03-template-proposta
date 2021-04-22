package br.com.xyzbank.proposal.createproposal;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProposalDetailResponse {

	private Long id;
	private String name;
	private String email;
	private BigDecimal salary;
	private ProposalStatus status;
	private AddressRequest address;

	@Deprecated
	public ProposalDetailResponse() {

	}

	public ProposalDetailResponse(Proposal proposal) {
		this.id = proposal.getId();
		this.name = proposal.getName();
		this.email = proposal.getEmail();
		this.salary = proposal.getSalary();
		this.status = proposal.getStatus();
		this.address = new AddressRequest(proposal.getStreet(), proposal.getNumber(),
				proposal.getZipCode(), proposal.getCity(), proposal.getState(),
				proposal.getCountry());
	}

	public Long getId() {
		return id;
	}

	public ProposalStatus getStatus() {
		return status;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public AddressRequest getAddress() {
		return this.address;
	}

}
