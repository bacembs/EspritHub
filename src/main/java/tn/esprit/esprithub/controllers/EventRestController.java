package tn.esprit.esprithub.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.esprithub.entities.Event;

import tn.esprit.esprithub.services.IEventService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/events")
public class EventRestController {

    private IEventService eventService;

    @Autowired
    public EventRestController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getallevents")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        Event event = eventService.getEventById(eventId);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public static String uploadDirectory= "C:\\Users\\HP\\IdeaProjects\\EspritHub\\src\\main\\resources\\static\\images";

    @PostMapping("/addevent")
    public Event addEvent(
            @RequestParam("titleEvent") String titleEvent,
            @RequestParam("capacityEvent") int capacityEvent,
            @RequestParam("descriptionEvent") String descriptionEvent,
            @RequestParam("locationEvent") String locationEvent,
            @RequestParam("dateEvent") LocalDateTime dateEvent,
            @RequestParam("images") MultipartFile img) throws IOException {
        Event event = new Event();
        event.setTitleEvent(titleEvent);
        event.setCapacityEvent(capacityEvent);
        event.setDescriptionEvent(descriptionEvent);
        event.setLocationEvent(locationEvent);
        event.setDateEvent(dateEvent);

        String imageName = "";

        if (!img.isEmpty()) {
            System.out.println("test 1");
            String originalFilename = img.getOriginalFilename();
            System.out.println("test 2");
            Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
            System.out.println("test 3");
            Files.write(fileNameAndPath, img.getBytes());
            System.out.println("test 4");
            // Utiliser le nom de fichier original comme nom de l'image de l'événement
            imageName = originalFilename;
        }

        event.setImgEvent(imageName);
        System.out.println("test final");
        return eventService.addEvent(event);
    }




    @PutMapping("/{eventId}")
    public ResponseEntity<Void> updateEvent(@PathVariable Long eventId, @RequestBody Event event) {
        Event existingEvent = eventService.getEventById(eventId);
        if (existingEvent != null) {
            event.setEventId(eventId); // Set the ID of the existing event
            eventService.updateEvent(event);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        Event existingEvent = eventService.getEventById(eventId);
        if (existingEvent != null) {
            eventService.deleteEvent(eventId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{eventId}/image")
    public ResponseEntity<byte[]> getEventImage(@PathVariable Long eventId) {
        // Récupérer les données binaires de l'image de l'événement
        byte[] imageBytes = eventService.getEventImageBytes(eventId);
        if (imageBytes != null && imageBytes.length > 0) {
            // Si les données de l'image existent et ne sont pas vides
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG) // ou MediaType.IMAGE_PNG selon le type de l'image
                    .body(imageBytes);
        } else {
            // Si l'image n'est pas trouvée ou si elle est vide
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



}