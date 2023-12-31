package com.example.projetfilrouge_Spring.repository;

import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    // NE SURTOUT PAS MODIFIER
    List<User> findByUsernameIsContainingIgnoreCase(String username);
    // NE SURTOUT PAS MODIFIER
    Optional<User> findByUsername(String username);

}
