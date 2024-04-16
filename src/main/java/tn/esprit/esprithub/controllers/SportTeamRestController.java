package tn.esprit.esprithub.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprithub.entities.Reservation;
import tn.esprit.esprithub.entities.SportTeam;
import tn.esprit.esprithub.entities.User;
import tn.esprit.esprithub.services.ISportTeamService;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/SportTeam")
public class SportTeamRestController {
    private ISportTeamService sportTeamService;
    @PostMapping("/add")
    public SportTeam addSportTeam (@RequestBody SportTeam sportTeam){
        return sportTeamService.addSportTeam(sportTeam);
    }
    @PutMapping("/update")
    public SportTeam updateSportTeam (@RequestBody SportTeam sportTeam){
        return sportTeamService.updateSportTeam(sportTeam);
    }
    @GetMapping("/get/{numSportTeam}")
    public SportTeam getSportTeam (@PathVariable Long numSportTeam){
        return sportTeamService.getSportTeamById(numSportTeam);
    }
    @DeleteMapping  ("/delete/{numSportTeam}")
    public void removeSportTeam (@PathVariable Long numSportTeam){
        sportTeamService.deleteSportTeam(numSportTeam);
    }

    @GetMapping  ("/all")
    public List<SportTeam> getAll (){
        return sportTeamService.getAll();}





    @PostMapping("/addCap/{captainId}")
    public SportTeam addSportTeamCap (@RequestBody SportTeam sportTeam,@PathVariable Long captainId){
        return sportTeamService.addSportTeamCap(sportTeam,captainId);
    }

    @PutMapping("/updateCap/{sportTeamId}")
    public SportTeam updateSportTeamCap (@RequestBody SportTeam sportTeam,@PathVariable Long sportTeamId){
        return sportTeamService.updateSportTeamCap(sportTeam,sportTeamId);
    }

//    @DeleteMapping  ("/deleteCap/{numSportTeam}")
//    public void deleteSportTeamCap (@PathVariable Long numSportTeam){
//        sportTeamService.deleteSportTeamCap(numSportTeam);
//    }

    @DeleteMapping("/deleteCap/{numSportTeam}/{captainId}")
    public void deleteSportTeamCap(@PathVariable Long numSportTeam, @PathVariable Long captainId) {
        sportTeamService.deleteSportTeamCap(numSportTeam, captainId);
    }








    @PostMapping("/addUser/{sportTeamId}/{userId}")
    public ResponseEntity<String> addUserToSportTeam(@PathVariable Long sportTeamId, @PathVariable Long userId) {
        sportTeamService.addUserToSportTeam(sportTeamId, userId);
        return ResponseEntity.ok("User added to sport team successfully.");
    }

    @PostMapping("/removeUser/{sportTeamId}/{userId}")
    public ResponseEntity<String> removeUserFromSportTeam(@PathVariable Long sportTeamId, @PathVariable Long userId) {
        sportTeamService.removeUserFromSportTeam(sportTeamId, userId);
        return ResponseEntity.ok("User removed from sport team successfully.");
    }



    @PostMapping("/participateSportTeam/{sportTeamId}")
    public ResponseEntity<String> participateSportTeam(@PathVariable Long sportTeamId, @RequestHeader("userId") Long userId) {
        sportTeamService.participateSportTeam(sportTeamId, userId);
        return ResponseEntity.ok("Participated in sport team successfully.");
    }


    @PostMapping("/cancelParticipateSportTeam/{sportTeamId}")
    public ResponseEntity<String> cancelParticipationSportTeam(@PathVariable Long sportTeamId, @RequestHeader("userId") Long userId) {
        sportTeamService.cancelParticipation(sportTeamId, userId);
        return ResponseEntity.ok("Cancelled participation from sport team successfully.");
    }






    @GetMapping("/{sportTeamId}/users")
    public ResponseEntity<?> getUsersBySportTeamId(@PathVariable Long sportTeamId) {
        List<User> users = sportTeamService.getUsersBySportTeamId(sportTeamId);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping("/{sportTeamId}/reservations")
//    public ResponseEntity<String> makeTeamReservation(
//            @PathVariable Long sportTeamId,
//            @RequestParam Long captainId,
//            @RequestBody Reservation reservation) {
//        try {
//            sportTeamService.makeTeamReservation(sportTeamId, captainId, reservation);
//            return ResponseEntity.ok("Team reservation created successfully.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while making the team reservation.");
//        }
//    }
//@PostMapping("/{sportTeamId}/reservations")
//public ResponseEntity<String> makeTeamReservation(
//        @PathVariable Long sportTeamId,
//        @RequestBody Reservation reservation) {
//    try {
//        sportTeamService.makeTeamReservation(sportTeamId, reservation);
//        return ResponseEntity.ok("Team reservation created successfully.");
//    } catch (IllegalArgumentException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while making the team reservation.");
//    }
//}

    @PostMapping("/{sportTeamId}/reservations")
    public ResponseEntity<String> makeTeamReservation(
            @PathVariable Long sportTeamId,
            @RequestParam Long captainId,
            @RequestParam Long fieldId,
            @RequestBody Reservation reservation) {
        try {
            sportTeamService.makeTeamReservation(sportTeamId, captainId, fieldId, reservation);
            return ResponseEntity.ok("Team reservation created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while making the team reservation.");
        }
    }

}

