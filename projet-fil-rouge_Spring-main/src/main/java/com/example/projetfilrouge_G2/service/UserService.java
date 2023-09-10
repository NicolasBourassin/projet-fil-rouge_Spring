package com.example.projetfilrouge_G2.service;

import com.example.projetfilrouge_G2.controller.model.TicketDto;
import com.example.projetfilrouge_G2.controller.model.UserDto;
import com.example.projetfilrouge_G2.repository.UserRepository;
import com.example.projetfilrouge_G2.repository.entity.Ticket;
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
    public List<UserDto> fetchAll() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> allUserDto = null;
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

}
