package br.com.xyzbank.proposal.managecard;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.xyzbank.proposal.shered.exceptionhandler.ApiErrorException;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;

@RestController
@RequestMapping("/api/card")
public class CardController {

	private final CardRepository cardRepository;
	private final CardFeign feignCard;

	public CardController(CardRepository cardRepository, CardFeign feignCard) {
		this.cardRepository = cardRepository;
		this.feignCard = feignCard;
	}

	@PostMapping("/{id}/block")
	public ResponseEntity<?> blockCard(@PathVariable("id") Long id,
			HttpServletRequest request, @Value("${card.apiException}") String reason) {
		var card = cardRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (card.getBlocked() != null)
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, reason);

		card.setBlocked(new BlockedCard(request.getRemoteAddr(),
				request.getHeader("User-Agent")));
		cardRepository.save(card);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{id}/notify/travel")
	public ResponseEntity<?> postMethodName(@PathVariable("id") Long id,
			@RequestBody NotifyTravelRequest dto, HttpServletRequest request) {
		var card = cardRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		try {
			var a = dto.toNotifyTravelFeign();
			var response = feignCard.notifyTravel(card.getCardNumber(), a);

			if (!response.isNotified())
				return ResponseEntity.badRequest().build();

			card.setTravel(dto.toNotifyTravel(request.getRemoteAddr(),
					request.getHeader("User-Agent")));
			cardRepository.save(card);
		} catch (FeignClientException | FeignServerException e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().build();
	}

}
