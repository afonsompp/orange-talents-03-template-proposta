package br.com.xyzbank.proposal.managecard;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletAssociationRepository
		extends JpaRepository<WalletAssociation, Long> {
	Optional<WalletAssociation> findByCardAndWallet(Card card, Wallet wallet);
}
