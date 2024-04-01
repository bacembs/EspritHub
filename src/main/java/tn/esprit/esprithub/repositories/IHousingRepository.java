package tn.esprit.esprithub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.esprithub.entities.Housing;

public interface IHousingRepository extends JpaRepository<Housing, Long> {
}
