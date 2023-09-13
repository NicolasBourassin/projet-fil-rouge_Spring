package com.example.projetfilrouge_Spring.service;

import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.UserRepository;

import com.example.projetfilrouge_Spring.repository.entity.User;
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
                userDto.getEmail(),
                userDto.getPurchaseHistory(),
                userDto.getSellingHistory());
        userRepository.save(userToAdd);
    }


    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<UserDto> findByUsernameIsContainingIgnoreCase(String username) {
        return userRepository.findByUsernameIsContainingIgnoreCase(username);
    }

    public Optional<UserDto> findById(Long id) {
        UserDto userDto = new UserDto(userRepository.findById(id));
        return Optional.of(userDto);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
