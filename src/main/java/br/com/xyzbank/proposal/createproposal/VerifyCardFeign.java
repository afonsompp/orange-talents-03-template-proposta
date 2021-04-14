package br.com.xyzbank.proposal.createproposal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card", url = "http://localhost:8888")
public interface VerifyCardFeign {

	@RequestMapping(method = RequestMethod.GET, value = "/api/cartoes")
	CardResponse verifyCard(@RequestParam Long idProposta);
}
