package br.com.xyzbank.proposal.managecard;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.xyzbank.proposal.shered.exceptionhandler.ApiErrorException;

@RestController
@RequestMapping("/api/card")
public class CardController {

	private final CardRepository cardRepository;

	public CardController(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
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

}
