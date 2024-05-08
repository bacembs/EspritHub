package tn.esprit.esprithub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.esprithub.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByUsername(String username);

    @Query("SELECT DISTINCT u.email " +
            "FROM User u JOIN u.reservations r " +
            "WHERE r.resStatus = 'cancelled' " +
            "GROUP BY u " +
            "HAVING COUNT(r) >= 5")    List<String> findUsersWithCancelledReservations();
}
