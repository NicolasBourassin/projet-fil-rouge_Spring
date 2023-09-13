package com.example.projetfilrouge_Spring.service;

import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.UserRepository;

import com.example.projetfilrouge_Spring.repository.entity.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<UserDto> fetchAll() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> allUserDto = new ArrayList<>();
        for (User user: allUsers) {
            UserDto userDto = new UserDto(Optional.ofNullable(user));
            allUserDto.add(userDto);
        }
        return allUserDto;
    }

    public UserDto fetchById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        UserDto userDto = new UserDto(user);
        return userDto;
    }

    public void save(UserDto userDto) {

        User userToAdd = new User(userDto.getUsername(),
                userDto.getPassword(),
                userDto.getPhoneNumber(),
                userDto.getPhotoUrl(),
                userDto.getEmail());
        userRepository.save(userToAdd);
    }

//    public void update(Long id, UserDto userDto) {
//        User userToUpdate = userRepository.getById(id);
//        userToUpdate.setId(id);
//        userToUpdate.setPassword(userDto.getPassword());
//        userToUpdate.setPhoneNumber(userDto.getPhoneNumber());
//        userToUpdate.setPhotoUrl(userDto.getPhotoUrl());
//        userToUpdate.setEmail(userDto.getEmail());
//        userRepository.save(userToUpdate);
//    }

    @Transactional
    public void update(Long id, UserDto userDto) {
        // Note : probably not necessary because it's also checked in RestController method
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        // verification for each attribute that a "null" value would not erase the current value

        // temporary variables to avoid confusion between getters & setters
        String password = userDto.getPassword();
        String phoneNumber = userDto.getPhoneNumber();
        String photoUrl = userDto.getPhotoUrl();
        String email = userDto.getEmail();
        if(password!=null){
            userToUpdate.setPassword(password);
        }
        if(phoneNumber!=null){
            userToUpdate.setPhoneNumber(phoneNumber);
        }
        if(photoUrl!=null){
            userToUpdate.setPhotoUrl(photoUrl);
        }
        if(email!=null){
            userToUpdate.setEmail(email);
        }

//        System.out.println("TEMP UserService : userDto = " + userDto.toString() );
//        System.out.println("TEMP UserService : userToUpdate = " + userToUpdate.toString() );

        userRepository.save(userToUpdate);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> findByUsernameIsContainingIgnoreCase(String username) {
        return userRepository.findByUsernameIsContainingIgnoreCase(username);
    }

    public UserDto findByUsername(String username){
        return userRepository.findByUsername(username);
    };

    public Optional<UserDto> findById(Long id) {
        UserDto userDto = new UserDto(userRepository.findById(id));
        return Optional.of(userDto);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
