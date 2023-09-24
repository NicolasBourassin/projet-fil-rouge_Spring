package com.example.projetfilrouge_Spring.repository;


import com.example.projetfilrouge_Spring.repository.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findTicketsByEventNameContainingIgnoreCaseAndDateBefore(String event, LocalDate current_date);
    List<Ticket> findTicketByEventNameContainingIgnoreCase(String eventName);
    List<Ticket> findTicketsByEventCityIgnoreCase(String eventCity);
    List<Ticket> findTicketsByEventTypeIgnoreCase(String eventType);
    List<Ticket> findTicketsByDateAfter(LocalDate date);
    Ticket findTopByOrderByIdDesc(); // return last Ticket added

    List<Ticket> findByTransactionCompleted(boolean completed); // return Ticket linked to a Transaction with
}
