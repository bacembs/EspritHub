package tn.esprit.esprithub.services;

import tn.esprit.esprithub.entities.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    User updateUserLock(Long userId, User user);
    User updateUserEnable(Long userId, User user);
    void deleteUser(Long userId);
    User getUserById(Long userId);
    List<User> getAll();
    User getByNom(String nomuser);
    String getUserAddressById(Long userId);
    Long getUserIdByName(String username);
    List<String> findUsersWithCancelledReservations();

}