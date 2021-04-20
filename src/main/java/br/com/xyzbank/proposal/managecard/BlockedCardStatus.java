package br.com.xyzbank.proposal.managecard;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BlockedCardStatus {

	WAITING,
	BLOCKED;

	@Deprecated
	@JsonCreator
	public static BlockedCardStatus fromString(String value) {
		if (value == null)
			throw new IllegalArgumentException();

		if (value.equalsIgnoreCase("BLOQUEADO"))
			return BLOCKED;

		throw new IllegalArgumentException();
	}
}
