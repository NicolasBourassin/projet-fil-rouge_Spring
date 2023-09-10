package com.example.projetfilrouge_G2.controller.api;

import com.example.projetfilrouge_G2.repository.TicketRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class TicketRestController {

    private TicketRepository ticketRepository;

    /*public TicketRestController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDto>> getAllTutorials(@RequestParam(value = "event", required = false) String searchedTitle) {

    }*/

}
