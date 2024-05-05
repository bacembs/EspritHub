package tn.esprit.esprithub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprithub.entities.Event;
import tn.esprit.esprithub.services.IEventService;

import java.util.Collection;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/event")
public class EventRestController {
	
	@Autowired
	IEventService eventInterface;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("event", eventInterface.retrieveAllEvents());
		return "event/index";
	}
	
	
	
	@GetMapping("/getAllEvent")
	@ResponseBody
	public List<Event> getAll() {
	List<Event> listevent = eventInterface.retrieveAllEvents();
	return listevent;
	}
	
	
	
	
	

	@GetMapping("/getAllEventActive")
	@ResponseBody
	public Collection<Event> getActive() {
		Collection<Event> listevent = eventInterface.retrieveAllEventsActive();
	return listevent;
	}
	
	@GetMapping("/getEventByCagnotte")
	@ResponseBody
	public Collection<Event> getEventByCagnotte() {
		Collection<Event> listevent = eventInterface.findEventByCagnotte();
	return listevent;
	}
	
	@GetMapping("/getEvent/{id}")
	@ResponseBody
	public Event retrieveEvent(@PathVariable("id") Long idEvent) {
	return eventInterface.retrieveEvent(idEvent);
	}
	
	@PostMapping("/add-getEvent")
	@ResponseBody
	public Event addEvent(@RequestBody Event e )
	{
		Event event = eventInterface.addEvent(e);
	return event;
	}
	
	@PostMapping("/add-getEvent/{cagnotte-id}")
	@ResponseBody
	public Event addEventCagnotte(@RequestBody Event e,@PathVariable("cagnotte-id") Long idCagnotte)
	{
	
		Event event = eventInterface.addCagnotteToEvent(e, idCagnotte);
	return event;
	}
	
	
	
	
	@DeleteMapping("/remove-event/{idEvent-id}")
	@ResponseBody
	public void removeEvent(@PathVariable("idEvent-id") Long idEvent) {
		eventInterface.removeEventt(idEvent);
	}
	
	@PutMapping("/modify-event")
	@ResponseBody
	public Event updateEvent(@RequestBody Event e) {
	return eventInterface.updateEvent(e);
	}
	
	
	@PutMapping("/updateEtat")
	@ResponseBody
	public void updateEtat() {
		eventInterface.updateEtat();
		
	}
	
/*	
	@PostMapping("/FaireReservation/{idReservation}/{idUser}/{idEvent}")
	public void FaireReservation(@PathVariable("idReservation")Long idreservation,@PathVariable("idUser") Long idUser,
	@PathVariable("idEvent") Long idEvent) {
		eventInterface.FaireReservation(idreservation,idUser, idEvent);
	}
	*/
	
	@GetMapping("/getNbParticipant/{id}")
	@ResponseBody

	public int NombreParticpEvent(@PathVariable("id") int idEvent) {
		
		return eventInterface.getNombreParticpEvent(idEvent);
	}
	
	@GetMapping("/getNbPlace/{id}")
	@ResponseBody

	public int NombrePlace(@PathVariable("id") int idEvent) {
		
		return eventInterface.getNombrePlacesEvent(idEvent);
	}
	
	@PostMapping("/add-getEvent-user/{cagnotte-id}/{user-id}")
	@ResponseBody
	public Event addEventUser(@RequestBody Event e,@PathVariable("cagnotte-id")Long idCagnotte,@PathVariable("user-id")Long idUser) {
		
		Event event = eventInterface.addEvent(e, idCagnotte, idUser);
		return event;
	}
	
}
