package tn.esprit.esprithub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.esprithub.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}