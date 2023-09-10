package com.example.projetfilrouge_Spring.controller.api;

import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAll()
    {
        if (userService.getAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return userService.getAll();
    }

    @GetMapping("/users/")
    @ResponseBody
    public List<UserDto> getByUsername(@RequestParam String username) {
        if (userService.findByUsernameIsContainingIgnoreCase(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userService.findByUsernameIsContainingIgnoreCase(username);
    }
    @GetMapping("/users/{id}")
    public UserDto getById(@PathVariable Long id) {
        if (userService.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userService.findById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody UserDto userDto) {
        userService.create(new UserDto(userDto.getUsername(), userDto.getPassword(),
                userDto.getPhoneNumber(), userDto.getPhotoUrl(), userDto.getEmail()));
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto updateById(@PathVariable Long id, @RequestBody UserDto updatedUserDto){
        return userService.update(id, updatedUserDto);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable Long id) {
        if (userService.findById(id)==null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        userService.deleteById(id);
    }
}
