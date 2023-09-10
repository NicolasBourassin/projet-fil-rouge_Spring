package com.example.projetfilrouge_G2.repository;

import com.example.projetfilrouge_G2.repository.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findTicketByEventContainingIgnoreCase(String event);
}
