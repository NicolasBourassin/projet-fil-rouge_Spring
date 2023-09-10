package com.example.projetfilrouge_G2.controller.api;

import com.example.projetfilrouge_G2.repository.UserRepository;
import com.example.projetfilrouge_G2.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/users")
    public List<User> getAll()
    {
    if (userRepo.findAll().isEmpty()){
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    return userRepo.findAll();
    }

    @GetMapping("/users/")
    @ResponseBody
    public List<User> getByUsername(@RequestParam String username) {
        if (userRepo.findByUsernameIsContainingIgnoreCase(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userRepo.findByUsernameIsContainingIgnoreCase(username);
    }
    @GetMapping("/users/{id}")
    public Optional<User> getById(@PathVariable Long id) {
        if (userRepo.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userRepo.findById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody User user) {
        userRepo.save(new User(user.getUsername(), user.getPassword(), user.getPhoneNumber(), user.getEmail()));
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@PathVariable Long id, @RequestBody User user){
        userRepo.save(new User(id,user.getUsername(), user.getPassword(), user.getPhoneNumber(), user.getEmail()));
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable Long id) {
        if (userRepo.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        userRepo.deleteById(id);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAll() {
        if (userRepo.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        userRepo.deleteAll();
    }

}
