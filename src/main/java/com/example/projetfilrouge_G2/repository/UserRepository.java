package com.example.projetfilrouge_G2.repository;

import com.example.projetfilrouge_G2.controller.model.UserDto;
import com.example.projetfilrouge_G2.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<UserDto> findByUsernameIsContainingIgnoreCase(String username);
}
