package br.com.xyzbank.proposal.createproposal;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
	Optional<Proposal> findByIdCard(String idCard);

	List<Proposal> findByStatusAndCard(ProposalStatus status, Card card);
}
