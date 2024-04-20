package tn.esprit.esprithub.service;

import tn.esprit.esprithub.entities.Event;

import java.util.List;

public interface EventServiceImppl {
    Event addEvent(Event event);
    Event updateEvent(Event event);
    void delete(Long eventId);
    Event getById(Long eventId);
    List<Event> getAll();

}
