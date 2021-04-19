package br.com.xyzbank.proposal.managecard;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card", url = "http://${contas.host}:${contas.port}")
public interface CardFeign {

	@RequestMapping(method = RequestMethod.GET, value = "/api/cartoes")
	CardResponse verifyCard(@RequestParam Long idProposta);

	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/bloqueios",
			consumes = "application/json")
	BlockedCardResponse blockCard(@PathVariable("id") String id,
			@RequestBody BlockedCardRequest dto);

	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/avisos",
			consumes = "application/json")
	NotifyTravelFeignResponse notifyTravel(@PathVariable("id") String id,
			@RequestBody NotifyTravelFeignRequest dto);
}
