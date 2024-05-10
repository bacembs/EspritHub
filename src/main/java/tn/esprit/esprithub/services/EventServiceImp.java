package tn.esprit.esprithub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.esprithub.entities.Event;
import tn.esprit.esprithub.repository.EventRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class EventServiceImp implements IEventService {

	private EventRepository eventRepository;
	private static final String uploadDirectory = "C:\\Users\\HP\\IdeaProjects\\EspritHub\\src\\main\\resources\\static\\images";

	@Override
	public byte[] getEventImageBytes(Long eventId) {
		// Récupérer l'événement par son ID
		Event event = eventRepository.findById(eventId).orElse(null);
		if (event != null) {
			// Chemin complet de l'image
			String imagePath = uploadDirectory + "\\" + event.getImgEvent();
			try {
				// Lire les données binaires de l'image
				return Files.readAllBytes(Paths.get(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Retourner un tableau de bytes vide si l'événement n'est pas trouvé ou s'il y a une erreur
		return new byte[0];
	}

	@Autowired
	public EventServiceImp(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	@Override
	public Event getEventById(Long eventId) {
		return eventRepository.findById(eventId).orElse(null);
	}

	@Override
	public Event addEvent(Event event) {
		return eventRepository.save(event);
	}
	@Override
	public void updateEvent(Event event) {
		eventRepository.save(event);
	}

	@Override
	public void deleteEvent(Long eventId) {
		eventRepository.deleteById(eventId);
	}

}