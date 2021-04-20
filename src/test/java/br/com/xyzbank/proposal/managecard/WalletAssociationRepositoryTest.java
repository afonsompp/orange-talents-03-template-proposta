package br.com.xyzbank.proposal.managecard;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@DataJpaTest
@ActiveProfiles("test")
class WalletAssociationRepositoryTest {

	@Autowired
	WalletAssociationRepository repository;
	@Autowired
	CardRepository cardRepository;

	@BeforeEach
	void beforeEach() {
		var card = cardRepository.save(new Card("cardNumber"));
		cardRepository.save(new Card("cardNumber2"));
		var wallet = new WalletAssociation("email", Wallet.PAYPAL, "associationId", card);
		repository.save(wallet);

	}

	@AfterEach
	void afterEach() {
		repository.deleteAll();
	}

	@DisplayName("Should return a optional of card if he exist associated with a wallet")
	@ParameterizedTest
	@MethodSource("provideParams")
	void test1(int get, Wallet w, boolean expected) {

		var cards = cardRepository.findAll();

		var optional = repository.findByCardAndWallet(cards.get(get), w);

		assertEquals(expected, optional.isPresent());
	}

	public static Stream<Arguments> provideParams() {

		return Stream.of(
				Arguments.of(0, Wallet.PAYPAL, true),
				Arguments.of(0, null, false),
				Arguments.of(1, Wallet.PAYPAL, false));
	}

}
