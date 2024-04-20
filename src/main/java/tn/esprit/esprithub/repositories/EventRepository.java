package tn.esprit.esprithub.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.esprithub.entities.Event;

public interface EventRepository extends CrudRepository<Event,Long> {
}
