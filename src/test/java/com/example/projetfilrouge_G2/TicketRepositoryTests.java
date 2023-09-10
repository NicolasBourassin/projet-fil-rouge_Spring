package com.example.projetfilrouge_G2;


import com.example.projetfilrouge_G2.controller.model.TicketDto;
import com.example.projetfilrouge_G2.repository.TicketRepository;
import com.example.projetfilrouge_G2.repository.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TicketRepositoryTests {
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testFindTicketsByEventContainingIgnoreCaseAndDateBefore() {
        //Arrange

        Ticket ticket1 = new Ticket(LocalDate.now().minusDays(1),"Event 1", 10.00F);
        Ticket ticket2 = new Ticket(LocalDate.now().plusDays(1), "Event 2", 10.00F);
        Ticket ticket3 = new Ticket(LocalDate.now().minusDays(2), "Event 3",10.00F);
        Ticket ticket4 = new Ticket(LocalDate.now().minusDays(2), "UNMATCHING", 10.00F);

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);
        ticketRepository.save(ticket3);
        ticketRepository.save(ticket4);
        // Assess
        List<Ticket> tickets = ticketRepository.findTicketsByEventContainingIgnoreCaseAndDateBefore("Event", LocalDate.now());

        // Assert
        // Check if search results contain the expected tickets
        assertEquals(2, tickets.size());
        assertTrue(tickets.contains(ticket1));
        assertTrue(tickets.contains(ticket3));
    }
}
