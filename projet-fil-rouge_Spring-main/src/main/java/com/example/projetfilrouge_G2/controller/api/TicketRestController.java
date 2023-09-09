package com.example.projetfilrouge_G2.controller.api;

import com.example.projetfilrouge_G2.repository.TicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api")
public class TicketRestController {

    private TicketRepository ticketRepository;

    public TicketRestController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDto>> getAllTutorials(@RequestParam(value = "event", required = false) String searchedTitle) {

}
