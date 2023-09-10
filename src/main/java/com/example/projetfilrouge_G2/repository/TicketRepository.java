package com.example.projetfilrouge_G2.repository;


import com.example.projetfilrouge_G2.controller.model.TicketDto;
import com.example.projetfilrouge_G2.repository.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<TicketDto> findTicketsByEventContainingIgnoreCaseAndDateBefore(String event, LocalDate current_date);
    List<TicketDto> findTicketByEventContainingIgnoreCase(String event);
}
