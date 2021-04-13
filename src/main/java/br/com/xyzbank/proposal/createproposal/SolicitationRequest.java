package br.com.xyzbank.proposal.createproposal;

public class SolicitationRequest {
	private String documento;
	private String nome;
	private String idProposta;

	@Deprecated
	public SolicitationRequest() {

	}

	public SolicitationRequest(Proposal proposal) {
		this.documento = proposal.getIdCard();
		this.nome = proposal.getName();
		this.idProposta = proposal.getId().toString();
	}

	public String getDocumento() {
		return this.documento;
	}

	public String getNome() {
		return this.nome;
	}

	public String getIdProposta() {
		return this.idProposta;
	}

}
