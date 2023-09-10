package com.example.projetfilrouge_Spring.service;

import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.UserRepository;
import com.example.projetfilrouge_Spring.repository.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Convertir User en UserDTO
    private UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getPhotoUrl(),
                user.getEmail()
        );
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto create(UserDto userDto)
    {
        // Convertir UserDTO en User
        User user = new User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getPhoneNumber(),
                userDto.getPhotoUrl(),
                userDto.getEmail()
        );

        User savedUser = userRepository.save(user);

        // Convertir User en UserDTO
        return convertToUserDto(savedUser);
    }

    @Transactional
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return convertToUserDto(user);
        }
        return null;
    }

    @Transactional
    public List<UserDto> findByUsernameIsContainingIgnoreCase(String username) {
        List<User> users = userRepository.findByUsernameIsContainingIgnoreCase(username);
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }


    @Transactional
    public UserDto update(Long id, UserDto updatedUserDto) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Mettez à jour les champs de l'utilisateur avec les valeurs de updatedUserDTO
            if (updatedUserDto.getUsername() != null) {
                user.setUsername(updatedUserDto.getUsername());
            }
            if (updatedUserDto.getPassword() != null) {
                user.setPassword(updatedUserDto.getPassword());
            }
            if (updatedUserDto.getPhoneNumber() != null) {
                user.setPhoneNumber(updatedUserDto.getPhoneNumber());
            }
            if (updatedUserDto.getPhotoUrl() != null) {
                user.setPhotoUrl(updatedUserDto.getPhotoUrl());
            }
            if (updatedUserDto.getEmail() != null) {
                user.setEmail(updatedUserDto.getEmail());
            }

            User updatedUser = userRepository.save(user);
            return convertToUserDto(updatedUser);
        }
        return null;
    }


}