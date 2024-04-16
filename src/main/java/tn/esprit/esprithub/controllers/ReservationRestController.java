package tn.esprit.esprithub.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprithub.entities.Reservation;
import tn.esprit.esprithub.entities.User;
import tn.esprit.esprithub.services.IReservationService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/Reservation")
public class ReservationRestController {
    private IReservationService reservationService;

    @PostMapping("/add")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @PutMapping("/update")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @GetMapping("/get/{numReservation}")
    public Reservation getReservation(@PathVariable Long numReservation) {
        return reservationService.getReservationById(numReservation);
    }

    @DeleteMapping("/delete/{numReservation}")
    public void removeReservation(@PathVariable Long numReservation) {
        reservationService.deleteReservation(numReservation);
    }

    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }


    @GetMapping("/allWithField")
    public List<Reservation> getAllReservationsWithField() {
        return reservationService.getAllReservationsWithField();
    }

    @GetMapping("/getWithField/{reservationId}")
    public ResponseEntity<Reservation> getReservationWithField(@PathVariable Long reservationId) {
        Reservation reservation = reservationService.getReservationWithField(reservationId);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/users/{userId}/{fieldId}/reserve")
    public ResponseEntity<Reservation> makeReservationForUser(@PathVariable Long userId,
                                                              @PathVariable Long fieldId,
                                                              @RequestBody Reservation reservation) {
        if (reservation == null || reservation.getStartDate() == null || reservation.getEndDate() == null || reservation.getNbPlayers() <= 0 ||
                reservation.getResStatus() == null || reservation.getResType() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Reservation savedReservation = reservationService.addReservationForUser(userId, fieldId, reservation);
        if (savedReservation != null) {
            return ResponseEntity.ok(savedReservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/users/{userId}/{reservationId}")
    public ResponseEntity<Void> deleteReservationForUser(@PathVariable Long userId,
                                                         @PathVariable Long reservationId) {
        reservationService.deleteReservationForUser(userId, reservationId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{userId}/{reservationId}")
    public ResponseEntity<Reservation> updateReservationForUser(@PathVariable Long userId,
                                                                @PathVariable Long reservationId,
                                                                @RequestBody Reservation updatedReservation) {
        Reservation updated = reservationService.updateReservationForUser(userId, reservationId, updatedReservation);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/users/{userId}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsForUser(@PathVariable Long userId) {
        List<Reservation> reservations = reservationService.getReservationsForUser(userId);
        if (reservations != null && !reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/reservations/fields/{fieldId}")
    public ResponseEntity<List<Reservation>> getReservationsByField(@PathVariable Long fieldId) {
        List<Reservation> reservations = reservationService.getReservationsByField(fieldId);
        if (!reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reservations/users/{userId}/fields/{fieldId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserAndField(@PathVariable Long userId,
                                                                           @PathVariable Long fieldId) {
        List<Reservation> reservations = reservationService.getReservationsByUserAndField(userId, fieldId);
        if (!reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reservations/startDateBetween")
    public ResponseEntity<List<Reservation>> getReservationsByStartDateBetween(@RequestParam("start") LocalDateTime start,
                                                                               @RequestParam("end") LocalDateTime end) {
        List<Reservation> reservations = reservationService.getReservationsByDateRange(start, end);
        if (!reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reservations/count/users/{userId}")
    public ResponseEntity<Long> countReservationsByUser(@PathVariable Long userId) {
        Long count = reservationService.countReservationsByUser(userId);
        return ResponseEntity.ok(count);
    }

    @PostMapping("/sportteams/{sportTeamId}/reserve")
    public ResponseEntity<Reservation> makeReservationForSportTeam(@PathVariable Long sportTeamId,
                                                                   @RequestBody Reservation reservation) {
        if (reservation == null || reservation.getStartDate() == null || reservation.getEndDate() == null || reservation.getNbPlayers() <= 0 ||
                reservation.getResStatus() == null || reservation.getResType() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        User captain = getCaptainOfSportTeam(sportTeamId).getBody();
        if (captain == null) {
            return ResponseEntity.notFound().build();
        }

        reservation.setUsers(new HashSet<>(Collections.singletonList(captain)));
        Reservation savedReservation = reservationService.addReservation(reservation);
        if (savedReservation != null) {
            return ResponseEntity.ok(savedReservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/sport-teams/{sportTeamId}/reservations")
    public ResponseEntity<Reservation> addReservationForSportTeam(@PathVariable Long sportTeamId, @RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.addReservationSportTeam(sportTeamId, reservation);
        return ResponseEntity.ok(createdReservation);
    }

    @GetMapping("/sport-teams/{sportTeamId}/captain")
    public ResponseEntity<User> getCaptainOfSportTeam(@PathVariable Long sportTeamId) {
        User captain = reservationService.getCaptainOfSportTeam(sportTeamId);
        if (captain != null) {
            return ResponseEntity.ok(captain);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{reservationId}/users")
    public ResponseEntity<Set<User>> getUsersByReservation(@PathVariable Long reservationId) {
        Set<User> users = reservationService.getUsersByReservation(reservationId);
        if (users != null && !users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{reservationId}/cancel")
    public ResponseEntity<String> cancelReservationForSportTeam(@PathVariable Long reservationId, @RequestParam Long captainId) {
        try {
            reservationService.cancelReservationForSportTeam(reservationId, captainId);
            return ResponseEntity.ok("Reservation canceled successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    @PostMapping("/cancelReservationsForToday")
    public ResponseEntity<String> cancelReservationsForToday() {
        try {

            reservationService.cancelReservationsForToday();


            return ResponseEntity.ok("Reservations for today have been cancelled if it's rainy.");
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel reservations for today.");
        }
    }

}