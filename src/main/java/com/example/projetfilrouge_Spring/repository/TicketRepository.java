package com.example.projetfilrouge_Spring.repository;


import com.example.projetfilrouge_Spring.repository.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findTicketsByEventContainingIgnoreCaseAndDateBefore(String event, LocalDate current_date);
    List<Ticket> findTicketByEventContainingIgnoreCase(String event);
}
