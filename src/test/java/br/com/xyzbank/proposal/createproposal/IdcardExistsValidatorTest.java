package br.com.xyzbank.proposal.createproposal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import br.com.xyzbank.proposal.shered.exceptionhandler.ApiErrorException;

class IdcardExistsValidatorTest {

	@Mock
	private ProposalRepository repository;

	private IdcardExistsValidator validator;

	private ProposalRequest target;

	private AddressRequest address;

	private Errors errors;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

		errors = new BeanPropertyBindingResult(target, "test");

		validator = new IdcardExistsValidator(repository, new BCryptPasswordEncoder());
		address = new AddressRequest("a", "1", "76920-000", "a", "a", "a");
		target = new ProposalRequest("", "aaa@bbb.com", "123", new BigDecimal(1),
				address);

		var proposals =
				Stream.of(target.toProposal())
						.collect(Collectors.toList());
		when(repository.findAll()).thenReturn(proposals);
	}

	@DisplayName("Should dont validate because have errors")
	@Test
	void test1() {
		errors.rejectValue(null, null);
		validator.validate(target, errors);

		assertEquals(1, errors.getErrorCount());
		verify(repository, times(0)).findAll();
	}

	@DisplayName("Should throw ApiErrorException if idCard already exists")
	@Test
	void test2() {
		assertThrows(ApiErrorException.class, () -> validator.validate(target, errors));
		verify(repository, times(1)).findAll();
	}

	@DisplayName("Should do not throw nothing if idCard do not exists")
	@Test
	void test3() {
		target = new ProposalRequest("a", "aa@bb.com", "124", new BigDecimal(1), address);

		assertDoesNotThrow(() -> validator.validate(target, errors));
		verify(repository, times(1)).findAll();
	}

}
