package br.com.xyzbank.proposal.createproposal;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.xyzbank.proposal.exceptionhandler.ApiErrorException;
import feign.FeignException.FeignClientException;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

	private final ProposalRepository repository;
	private final SolicitationFeign solicitation;

	public ProposalController(ProposalRepository repository,
			SolicitationFeign solicitation) {
		this.repository = repository;
		this.solicitation = solicitation;
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

	@GetMapping("/{id}")
	public ResponseEntity<ProposalStatusResponse> reviewProposal(
			@PathVariable("id") Long id) {
		var optional = repository.findById(id);
		var proposal = optional
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		try {
			solicitation.solicitation(new SolicitationRequest(proposal));
			proposal.setStatus(ProposalStatus.ELIGIBLE);
		} catch (FeignClientException e) {
			if (e.status() != HttpStatus.UNPROCESSABLE_ENTITY.value()) {
				throw e;
			}
			proposal.setStatus(ProposalStatus.NOT_ELIGIBLE);
		}
		var response = repository.save(proposal);

		return ResponseEntity.ok(new ProposalStatusResponse(response));

	}
}
