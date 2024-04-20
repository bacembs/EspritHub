package tn.esprit.esprithub.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.esprithub.entities.Event;
import tn.esprit.esprithub.repositories.EventRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class EventService implements EventServiceImppl{
    @Autowired
    EventRepository evRep;
    @Override
    public Event addEvent(Event event) {
        return evRep.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return evRep.save(event);
    }

    @Override
    public void delete(Long eventId) {
        evRep.deleteById(eventId);

    }

    @Override
    public Event getById(Long eventId) {
        return null;
    }

    @Override
    public List<Event> getAll() {
        return (List<Event>) evRep.findAll();
    }
}
