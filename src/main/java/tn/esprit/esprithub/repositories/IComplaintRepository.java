package tn.esprit.esprithub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.esprithub.entities.Complaint;
import tn.esprit.esprithub.entities.User;

import java.util.Optional;

public interface IComplaintRepository extends JpaRepository<Complaint,Long> {
    Optional <Complaint> findByEmail(String email);
}