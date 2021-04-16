package br.com.xyzbank.proposal.managecard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometryRepository extends JpaRepository<Biometry, Long> {

}
