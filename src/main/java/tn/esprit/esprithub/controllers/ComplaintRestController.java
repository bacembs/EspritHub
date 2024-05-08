package tn.esprit.esprithub.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprithub.entities.Complaint;
import tn.esprit.esprithub.entities.User;
import tn.esprit.esprithub.services.ComplaintService;
import tn.esprit.esprithub.services.IComplaintService;
import tn.esprit.esprithub.services.IUserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Complaint")
public class ComplaintRestController {
    private IComplaintService complaintService;
    private IUserService userService;

    @PostMapping("/add")
    public Complaint addComplaint (@RequestBody Complaint complaint){
        return complaintService.addComplaint(complaint);
    }
    @PutMapping("/update")
    public Complaint updateComplaint (@RequestBody Complaint complaint){
        return complaintService.updateComplaint(complaint);
    }
    @GetMapping("/{complaintId}")
    public Complaint getComplaint (@PathVariable Long complaintId){
        return complaintService.getComplaintById(complaintId);
    }
    @DeleteMapping  ("/delete/{complaintId}")
    public void removeComplaint (@PathVariable Long complaintId){
        complaintService.deleteComplaint(complaintId);
    }
    @GetMapping  ("/all")
    public List<Complaint> getAll (){
        return complaintService.getAll();}

    @GetMapping("/reservations-complaint")
    public List<String> findUsersWithCancelledReservations() {
        return userService.findUsersWithCancelledReservations();
    }

    @PostMapping("/users-with-cancelled-reservations/save-complaints")
    public List<String> saveComplaintsForUsersWithCancelledReservations() {
        return userService.findUsersWithCancelledReservations();
    }

}