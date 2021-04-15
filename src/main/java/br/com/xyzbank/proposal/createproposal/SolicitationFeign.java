package br.com.xyzbank.proposal.createproposal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "solicitation", url = "http://${analise.host}:${analise.port}")
public interface SolicitationFeign {

	@RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
	SolicitationResponse solicitation(@RequestBody SolicitationRequest request);
}
