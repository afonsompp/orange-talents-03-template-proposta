package br.com.xyzbank.proposal.managecard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;

@RestController
@RequestMapping("/api/card")
public class WalletAssociationController {
	private final CardFeign cardFeign;
	private CardRepository cardRepository;
	private WalletAssociationRepository walletRepository;

	public WalletAssociationController(CardFeign cardFeign, CardRepository cardRepository,
			WalletAssociationRepository walletRepository) {
		this.cardFeign = cardFeign;
		this.cardRepository = cardRepository;
		this.walletRepository = walletRepository;
	}

	@PostMapping("/{id}/wallet")
	public ResponseEntity<?> postMethodName(
			@PathVariable("id") Long id, @RequestBody WalletRequest dto) {
		var card = cardRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		var association = walletRepository.findByCardAndWallet(card, dto.getWallet());

		if (association.isPresent())
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

		try {
			var response = cardFeign.walletAssociation(card.getCardNumber(),
					dto.toWalletFeignRequest());

			var wallet = walletRepository
					.save(dto.toWalletAssociation(response.getId(), card));

			var url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(wallet.getId()).toUri();

			return ResponseEntity.created(url).build();

		} catch (FeignClientException | FeignServerException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
