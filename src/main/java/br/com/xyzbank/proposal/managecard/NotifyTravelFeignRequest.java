package br.com.xyzbank.proposal.managecard;

public class NotifyTravelFeignRequest {

	private String destino;
	private String validoAte;

	@Deprecated
	public NotifyTravelFeignRequest() {

	}

	public NotifyTravelFeignRequest(String destino, String validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return this.destino;
	}

	public String getValidoAte() {
		return this.validoAte;
	}

}
