package tn.esprit.esprithub.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.esprithub.entities.Feedback;
import tn.esprit.esprithub.entities.Transaction;

public interface ItransactionRepository extends CrudRepository<Transaction, Long> {
}