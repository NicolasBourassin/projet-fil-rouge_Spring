package com.example.projetfilrouge_Spring;

import com.example.projetfilrouge_Spring.controller.api.TicketRestController;
import com.example.projetfilrouge_Spring.controller.model.TicketDto;
import com.example.projetfilrouge_Spring.repository.entity.Ticket;
import com.example.projetfilrouge_Spring.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class TicketRestControllerTest {

    @InjectMocks
    private TicketRestController ticketRestController;

    @Mock
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        // Arrange
        Ticket ticket1 = new Ticket(LocalDate.parse("2024-02-24"), "BIG EVENT", 30.30F);
        Ticket ticket2 = new Ticket(LocalDate.parse("2024-04-22"), "small event", 30.30F);

        TicketDto ticketDto1 = new TicketDto(Optional.of(ticket1));
        TicketDto ticketDto2 = new TicketDto(Optional.of(ticket2));
        List<TicketDto> ticketList = Arrays.asList(ticketDto1, ticketDto2);

        when(ticketService.fetchAll()).thenReturn(ticketList);

        // Act
        List<TicketDto> result = ticketRestController.getAll();

        // Assert
        assertEquals(2, result.size());
        // Add more assertions as needed to verify the behavior of the getAll method.
    }

    // Similar test methods for other controller methods like getByEvent, getById, add, updateById, and deleteById

    // Example test for a specific scenario
    @Test
    void testGetAllEmpty() {
        // Arrange
        when(ticketService.fetchAll()).thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> {
            ticketRestController.getAll();
        });
    }
}