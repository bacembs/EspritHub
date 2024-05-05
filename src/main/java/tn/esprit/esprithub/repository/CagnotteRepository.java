package tn.esprit.esprithub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprithub.entities.Cagnotte;

@Repository
public interface CagnotteRepository extends JpaRepository<Cagnotte,Long> {

}
