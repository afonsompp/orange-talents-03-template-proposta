package br.com.xyzbank.proposal.managecard;

public enum BlockedCardStatus {

	WAITING,
	BLOCKED;

	@Deprecated
	public static BlockedCardStatus fromString(String value) {
		if (value == null)
			throw new IllegalArgumentException();

		if (value.equalsIgnoreCase("BLOQUEADO"))
			return BLOCKED;

		throw new IllegalArgumentException();
	}
}
