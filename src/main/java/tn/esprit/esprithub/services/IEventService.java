package tn.esprit.esprithub.services;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.esprithub.entities.Event;

import java.io.IOException;
import java.util.List;

public interface IEventService {
	List<Event> getAllEvents();
	Event getEventById(Long eventId);
	Event addEvent(Event event);
	void updateEvent(Event event);
	void deleteEvent(Long eventId);

	byte[] getEventImageBytes(Long eventId);



}
