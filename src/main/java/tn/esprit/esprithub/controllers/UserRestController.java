package tn.esprit.esprithub.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprithub.entities.User;
import tn.esprit.esprithub.services.IUserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/User")
public class UserRestController {
    private IUserService userService;

    @PostMapping("/add")
    public User addUser (@RequestBody User user){
        return userService.addUser(user);
    }
    @PutMapping ("/update/{userId}")
    public User updateUser (@PathVariable Long userId, @RequestBody User user){return userService.updateUser(userId, user);}
    @GetMapping  ("/{userId}")
    public User getUser (@PathVariable Long userId){
        return userService.getUserById(userId);
    }
    @DeleteMapping  ("/delete/{userId}")
    public void removeUser (@PathVariable Long userId){
        userService.deleteUser(userId);
    }
    @GetMapping  ("/all")
    public List<User> getAll (){
        return userService.getAll();}
    @GetMapping("/user")
    public User getbyuser(@RequestBody User user){
        return userService.getByNom(user.getUsername());
    }
}