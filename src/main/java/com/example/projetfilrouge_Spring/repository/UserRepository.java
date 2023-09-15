package com.example.projetfilrouge_Spring.repository;

import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//TODO fix problem by choosing between dto et user
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<UserDto> findByUsernameIsContainingIgnoreCase(String username);
    Optional<UserDto> findByUsernameIsContaining(String username);

}
