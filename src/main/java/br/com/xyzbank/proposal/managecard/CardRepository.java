package br.com.xyzbank.proposal.managecard;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	Set<Card> findByBlockedStatus(BlockedCardStatus status);
}
