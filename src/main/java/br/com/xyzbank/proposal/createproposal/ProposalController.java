package br.com.xyzbank.proposal.createproposal;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.xyzbank.proposal.exceptionhandler.ApiErrorException;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

	private final ProposalRepository repository;

	public ProposalController(ProposalRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<ProposalResponse> save(
			@RequestBody @Valid ProposalRequest dto,
			@Value("${api.exception.raeson}") String reason) {

		if (repository.findByIdCard(dto.getIdCard()).isPresent()) {
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, reason,
					"idCard");
		}

		var proposal = repository.save(dto.toProposal());

		var url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(proposal.getId()).toUri();

		return ResponseEntity.created(url).body(new ProposalResponse(proposal));
	}
}
