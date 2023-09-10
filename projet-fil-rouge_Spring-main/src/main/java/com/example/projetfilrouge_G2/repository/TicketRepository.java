package com.example.projetfilrouge_G2.repository;


import com.example.projetfilrouge_G2.repository.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //TODO : tester combinaison de 2 méthodes :
    List<Ticket> findTicketsByEventContainingAndDateBefore(String event, LocalDate current_date);





}
