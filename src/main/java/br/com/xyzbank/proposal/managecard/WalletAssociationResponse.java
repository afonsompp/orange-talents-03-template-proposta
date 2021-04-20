package br.com.xyzbank.proposal.managecard;

public class WalletAssociationResponse {

	private String resultado;
	private String id;

	@Deprecated
	public WalletAssociationResponse() {}

	public WalletAssociationResponse(String resultado, String id) {
		this.resultado = resultado;
		this.id = id;
	}

	public String getResultado() {
		return this.resultado;
	}

	public String getId() {
		return this.id;
	}

}
