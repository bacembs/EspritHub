package tn.esprit.esprithub.services;


import tn.esprit.esprithub.entities.Event;

import java.util.Collection;
import java.util.List;

public interface IEventService {
	List<Event> retrieveAllEvents();

	Event addEvent(Event e);
	
	Event addCagnotteToEvent (Event e,Long idCagnotte);
	

	Event updateEvent(Event e);

	Event retrieveEvent(Long id);

	void removeEventt(Long id);
	
	public Event addEventC(Event e,Long idCagnotte );
	public Event addEvent(Event e,Long idCagnotte,Long idUser);
	
	Collection<Event> retrieveAllEventsActive();
	
	void updateEtat();
	
	
	public Collection<Event> findEventByCagnotte();
	
	public void ExpiredEvent();
	
//public void FaireReservation(Long idReservation ,Long idUser,Long idEvent);
	
	int getNombrePlacesEvent(int idevent);

	int getNombreParticpEvent(int idevent);
	
	
	


	
	

}
