package br.com.xyzbank.proposal.createproposal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import br.com.xyzbank.proposal.managecard.Card;

@DataJpaTest
@ActiveProfiles("test")
class ProposalRepositoryTest {

	@Autowired
	private ProposalRepository repository;

	@BeforeEach
	void beforeEach() {

	}

	@AfterEach
	void afterEach() {
		repository.deleteAll();
	}

	@DisplayName("Should return proposal when she goes eligible and have no associate card")
	@ParameterizedTest
	@MethodSource("provideProposalStatusAndCard")
	void test2(ProposalStatus status, Card card, Boolean empty) {
		Proposal p1 = new Proposal("", "", "1", new BigDecimal(5),
				new AddressRequest("", "", "", "", "", ""));
		p1.setCard(new Card("2123123123"));
		p1.setStatus(ProposalStatus.ELIGIBLE);
		Proposal p2 = new Proposal("", "", "2", new BigDecimal(5),
				new AddressRequest("", "", "", "", "", ""));
		p2.setCard(new Card("12345"));
		p2.setStatus(ProposalStatus.ELIGIBLE);

		repository.save(p1);
		var proposal = repository.save(p2);

		card = card == null ? proposal.getCard() : null;

		var proposals = repository.findByStatusAndCard(status, card);
		assertEquals(empty, proposals.isEmpty());

	}

	private static Stream<Arguments> provideProposalStatusAndCard() {
		Proposal prop = new Proposal("", "", "223.532.411-31", new BigDecimal(5),
				new AddressRequest("street", "number", "zipCode", "city", "state",
						"country"));
		prop.setCard(new Card("12345"));
		prop.setStatus(ProposalStatus.ELIGIBLE);
		var list = new ArrayList<Proposal>();
		list.add(prop);
		return Stream.of(
				Arguments.of(ProposalStatus.ELIGIBLE, new Card("54321"), true),
				Arguments.of(ProposalStatus.NOT_ELIGIBLE, null, true),
				Arguments.of(ProposalStatus.ELIGIBLE, null, false));
	}

}
