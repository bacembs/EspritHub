package tn.esprit.esprithub.services;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.esprithub.entities.Complaint;
import tn.esprit.esprithub.entities.Cstatus;
import tn.esprit.esprithub.entities.User;
import tn.esprit.esprithub.repositories.IComplaintRepository;
import tn.esprit.esprithub.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class UserService implements IUserService {
    private IUserRepository userRepository;
    private IComplaintRepository complaintRepository;
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }


    public User updateUserLock(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId).orElse(null);
        existingUser.setAccountLocked(!existingUser.isAccountLocked());
        return userRepository.save(existingUser);
    }

    public User updateUserEnable(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId).orElse(null);
        existingUser.setEnabled(!existingUser.isEnabled());
        return userRepository.save(existingUser);
    }



    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public String getUserFullName(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getFirstName() + " " + user.getLastName() +" "+ user.getUserId();
        }
        return "";
    }


    @Override
    public Long getUserIdByName(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getUserId();
        }
        return null;
    }

    @Override
    public User getByNom(String nomuser) {
        return userRepository.findByUsername(nomuser);
    }


//    public List<String> findUsersWithCancelledReservations() {
//        List<String> usersWithEmails = userRepository.findUsersWithCancelledReservations();
//        List<String> savedEmails = new ArrayList<>();
//
//        for (String email : usersWithEmails) {
//            Optional<User> userOptional = userRepository.findByEmail(email); // Fetch user by email
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                Complaint complaint = new Complaint();
//                complaint.setEmail(email);
//
//                complaint.setDescriptionComplaint("Your default description"); // Set default description
//                complaint.setStatusComplaint(Cstatus.pending); // Set default status
//                complaintRepository.save(complaint);
//                savedEmails.add(email);
//            }
//        }
//
//        return savedEmails;
//    }


    public void executeTask() {
        findUsersWithCancelledReservations();

    }
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    @Override
    public List<String> findUsersWithCancelledReservations() {
        List<String> usersWithEmails = userRepository.findUsersWithCancelledReservations();
        List<String> savedEmails = new ArrayList<>();

        for (String email : usersWithEmails) {
            Optional<User> userOptional = userRepository.findByEmail(email); // Fetch user by email
            if (userOptional.isPresent()) {
                User user = userOptional.get();

                // Check if a complaint already exists for this email
                Optional<Complaint> existingComplaint = complaintRepository.findByEmail(email);

                if (!existingComplaint.isPresent()) {
                    Complaint complaint = new Complaint();
                    complaint.setEmail(email);
                    complaint.setDescriptionComplaint("Malicious activity detected");
                    complaint.setStatusComplaint(Cstatus.pending);
                    complaintRepository.save(complaint);
                    savedEmails.add(email);
                    System.out.println(email);
                } else {
                    // Handle case where complaint already exists for this email
                    // You may log an error or skip saving the complaint
                    // For example:
                    System.out.println("Complaint already exists for email: " + email);
                }
            }
        }

        return savedEmails;
    }




}