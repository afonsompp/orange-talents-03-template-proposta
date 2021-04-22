package br.com.xyzbank.proposal.createproposal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.xyzbank.proposal.managecard.Card;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

	List<Proposal> findByStatusAndCard(ProposalStatus status, Card card);
}
