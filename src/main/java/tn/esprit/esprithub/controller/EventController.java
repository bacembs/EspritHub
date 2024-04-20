package tn.esprit.esprithub.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprithub.entities.Event;
import tn.esprit.esprithub.service.EventService;

import java.util.List;

@RequestMapping("/event")
@AllArgsConstructor
@RestController

public class EventController {
    @Autowired
    private EventService evSer;

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event){
        return evSer.addEvent(event);
    }
    @PutMapping("/update")
    public Event updateEvent(@RequestBody Event event){
        return evSer.updateEvent(event);
    }
    @DeleteMapping("/delete/{eventId}")
    public void removeEvent(@PathVariable Long eventId){
        evSer.delete(eventId);
    }
    @GetMapping("/get/all")
    public List<Event> getAll(){
        return evSer.getAll();
    }
}
