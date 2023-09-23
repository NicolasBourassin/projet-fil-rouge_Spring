package com.example.projetfilrouge_Spring.repository;


import com.example.projetfilrouge_Spring.repository.entity.Ticket;
import com.example.projetfilrouge_Spring.repository.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTopByOrderByIdDesc(); // return last Ticket added
}