package br.com.xyzbank.proposal.createproposal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import br.com.xyzbank.proposal.shered.exceptionhandler.ApiErrorException;

@Component
public class IdcardExistsValidator implements Validator {

	private final ProposalRepository repository;
	private final PasswordEncoder encoder;
	@Value("${proposal.apiException}")
	private String reason;

	public IdcardExistsValidator(ProposalRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ProposalRequest.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors())
			return;

		var dto = (ProposalRequest) target;
		var proposals = repository.findAll();
		proposals.forEach(p -> {
			if (encoder.matches(dto.getIdCard(), p.getIdCard()))
				throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, reason,
						"idCard");
		});
	}
}
