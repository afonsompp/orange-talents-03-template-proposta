package br.com.xyzbank.proposal.createproposal;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import br.com.xyzbank.proposal.shered.validation.CpfOrCnpj;

public class ProposalRequest {

	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@CpfOrCnpj
	private String idCard;
	@NotNull
	@PositiveOrZero
	private BigDecimal salary;
	@Valid
	private AddressRequest address;

	@Deprecated
	public ProposalRequest() {

	}

	public ProposalRequest(String name, String email, String idCard, BigDecimal salary,
			AddressRequest address) {
		this.name = name;
		this.email = email;
		this.idCard = idCard;
		this.salary = salary;
		this.address = address;
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

	public Proposal toProposal() {
		return new Proposal(name, email, idCard, salary, address);
	}

}
