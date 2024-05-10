package tn.esprit.esprithub.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.esprithub.entities.Event;


@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

}
