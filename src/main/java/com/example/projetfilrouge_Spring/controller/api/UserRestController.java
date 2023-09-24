package com.example.projetfilrouge_Spring.controller.api;

import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.entity.User;
import com.example.projetfilrouge_Spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserRestController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    public UserRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public List<UserDto> getAll()
    {
        if (userService.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return userService.findAll();
    }

    @GetMapping("/users?")
    @ResponseBody
    public List<UserDto> getByUsernameIsContainingIgnoreCase(@RequestParam String username) {
        if (userService.findByUsernameIsContainingIgnoreCase(username) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userService.listToDto(userService.findByUsernameIsContainingIgnoreCase(username));
    }

    @GetMapping("/users/{id}")
    public Optional<UserDto> getById(@PathVariable Long id) {
        if (userService.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userService.findById(id);
    }

    @PostMapping("/users/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody UserDto userDto) {
        System.out.println("REGISTER CALLED, userDto :"+userDto.toString());
        userService.save(new UserDto(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),
                userDto.getPhoneNumber(), userDto.getPhotoUrl(), userDto.getEmail(),
                userDto.getRoleList(), userDto.getPurchaseHistory(), userDto.getSellingHistory()));
    }

    // fixme : probably not safe : verify if a connected User can manually modify the url {id} to update another User
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserDto> updateById(@PathVariable("id") Long id, @RequestBody UserDto userDto){
        Optional<UserDto> updateTarget = userService.findById(id);
        if (updateTarget.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserDto updateVersion = updateTarget.get();
        //NOTE : here the User is allowed to modify : password, phone number, photoUrl, email, and nothing else.
        updateVersion.setPassword( passwordEncoder.encode(userDto.getPassword()) );
        updateVersion.setPhoneNumber(userDto.getPhoneNumber());
        updateVersion.setPhotoUrl(userDto.getPhotoUrl());
        updateVersion.setEmail(userDto.getEmail());

        //TODO TEMP
        System.out.println("TEMP UserRestController : UserDto = " + updateVersion.toString() );
        userService.update(id, updateVersion);
        return ResponseEntity.status(HttpStatus.OK).body(updateVersion);
    }

    // fixme : probably not safe : verify if a connected User can manually modify the url {id} to suppr another User
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        Optional<UserDto> userDto = userService.findById(id);
        if (userDto.isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
