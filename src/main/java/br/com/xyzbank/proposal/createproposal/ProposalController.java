package br.com.xyzbank.proposal.createproposal;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import feign.FeignException.FeignClientException;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

	private final ProposalRepository repository;
	private final SolicitationFeign solicitation;
	private IdcardExistsValidator validator;

	public ProposalController(ProposalRepository repository,
			SolicitationFeign solicitation, IdcardExistsValidator validator) {
		this.repository = repository;
		this.solicitation = solicitation;
		this.validator = validator;
	}

	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	@PostMapping("/step-1")
	public ResponseEntity<ProposalResponse> save(
			@RequestBody @Valid ProposalRequest dto) {
		var proposal = repository.save(dto.toProposal());

		var url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(proposal.getId()).toUri();

		return ResponseEntity.created(url).body(new ProposalResponse(proposal));
	}

	@GetMapping("/step-2/{id}")
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

	@GetMapping("/{id}")
	public ResponseEntity<ProposalDetailResponse> showProposal(
			@PathVariable("id") Long id) {
		var optional = repository.findById(id);
		var proposal = optional
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return ResponseEntity.ok(new ProposalDetailResponse(proposal));

	}
}
