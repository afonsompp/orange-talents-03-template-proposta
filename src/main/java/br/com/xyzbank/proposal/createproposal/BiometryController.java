package br.com.xyzbank.proposal.createproposal;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/card")
public class BiometryController {

	private final BiometryInBase64Validator biometryValidator;
	private final CardRepository cardRepository;
	private final BiometryRepository biometryRepository;

	public BiometryController(BiometryInBase64Validator biometryValidator,
			CardRepository cardRepository, BiometryRepository biometryRepository) {
		this.biometryValidator = biometryValidator;
		this.cardRepository = cardRepository;
		this.biometryRepository = biometryRepository;
	}

	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.addValidators(biometryValidator);
	}

	@PostMapping("/{id}/biometry")
	public ResponseEntity<BiometryResponse> createBiometry(
			@RequestBody @Valid BiometryRequest dto, @PathVariable("id") Long id) {
		var card = cardRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		var result = biometryRepository.save(dto.toBiometry(card));

		var url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(url).body(new BiometryResponse(result));
	}

}
