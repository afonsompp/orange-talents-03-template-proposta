package br.com.xyzbank.proposal.managecard;

import com.fasterxml.jackson.annotation.JsonCreator;

public class NotifyTravelFeignResponse {
	private String resultado;

	@Deprecated
	public NotifyTravelFeignResponse() {

	}

	@JsonCreator
	public NotifyTravelFeignResponse(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return this.resultado;
	}

	public Boolean isNotified() {
		if (resultado == null)
			return false;
		return resultado.equalsIgnoreCase("CRIADO");
	}

}
