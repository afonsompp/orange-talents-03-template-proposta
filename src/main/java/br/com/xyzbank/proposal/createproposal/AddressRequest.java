package br.com.xyzbank.proposal.createproposal;

import javax.validation.constraints.NotBlank;
import br.com.xyzbank.proposal.shered.validation.ZipCode;

public class AddressRequest {

	@NotBlank
	private String street;
	@NotBlank
	private String number;
	@NotBlank
	@ZipCode
	private String zipCode;
	@NotBlank
	private String city;
	@NotBlank
	private String state;
	@NotBlank
	private String country;

	@Deprecated
	public AddressRequest() {

	}

	public AddressRequest(String street, String number, String zipCode, String city,
			String state, String country) {
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
		this.country = country;
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
