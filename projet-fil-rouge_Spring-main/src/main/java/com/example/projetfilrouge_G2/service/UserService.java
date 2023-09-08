package com.example.projetfilrouge_G2.service;


import com.example.projetfilrouge_G2.controller.model.UserDto;
import com.example.projetfilrouge_G2.repository.UserRepository;
import com.example.projetfilrouge_G2.repository.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> fetchAll() {
        return userRepository.findAll();
    }

    public UserDto fetchById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        UserDto userDto = new UserDto(user);
        return userDto;
    }

}
