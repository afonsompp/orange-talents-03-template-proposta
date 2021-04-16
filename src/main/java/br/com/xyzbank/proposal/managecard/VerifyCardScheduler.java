package br.com.xyzbank.proposal.managecard;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import br.com.xyzbank.proposal.createproposal.ProposalRepository;
import br.com.xyzbank.proposal.createproposal.ProposalStatus;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;

@Service
public class VerifyCardScheduler {

	private final ProposalRepository repository;
	private final VerifyCardFeign verifyCard;

	public VerifyCardScheduler(ProposalRepository repository,
			VerifyCardFeign verifyCard) {

		this.repository = repository;
		this.verifyCard = verifyCard;
	}

	@Scheduled(fixedDelay = 10000)
	public void verifyCard() {
		var proposals = repository.findByStatusAndCard(ProposalStatus.ELIGIBLE, null);
		proposals.forEach(p -> {
			try {
				var cardNumber = verifyCard.verifyCard(p.getId());
				p.setCard(cardNumber.toCard());
			} catch (FeignClientException | FeignServerException e) {
			}

		});
		repository.saveAll(proposals);
	}
}
