package com.example.projetfilrouge_G2.repository;

import com.example.projetfilrouge_G2.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
