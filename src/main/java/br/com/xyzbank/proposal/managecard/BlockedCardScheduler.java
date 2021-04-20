package br.com.xyzbank.proposal.managecard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;

@Service
public class BlockedCardScheduler {

	private final CardRepository repository;
	private final CardFeign verifyCard;
	@Value("${application.name}")
	public String system;

	public BlockedCardScheduler(CardRepository repository, CardFeign verifyCard) {
		this.repository = repository;
		this.verifyCard = verifyCard;
	}

	@Scheduled(fixedDelay = 10000)
	public void blockCard() {
		var cards = repository.findByBlockedStatus(BlockedCardStatus.WAITING);
		cards.forEach(c -> {
			try {
				verifyCard.blockCard(c.getCardNumber(), new BlockedCardRequest(system));
				c.getBlocked().setStatus(BlockedCardStatus.BLOCKED);
			} catch (FeignClientException | FeignServerException e) {
			}
		});
		repository.saveAll(cards);
	}
}
