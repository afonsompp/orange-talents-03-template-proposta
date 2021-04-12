package br.com.xyzbank.proposal.createproposal;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

	private final ProposalRepository repository;

	public ProposalController(ProposalRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<ProposalResponse> save(
			@RequestBody @Valid ProposalRequest dto) {

		var proposal = repository.save(dto.toProposal());

		var url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(proposal.getId()).toUri();

		return ResponseEntity.created(url).body(new ProposalResponse(proposal));
	}
}
