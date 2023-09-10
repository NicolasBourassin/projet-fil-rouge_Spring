package com.example.projetfilrouge_G2.controller.api;

import com.example.projetfilrouge_G2.repository.TicketRepository;
import com.example.projetfilrouge_G2.repository.UserRepository;
import com.example.projetfilrouge_G2.repository.entity.Ticket;
import com.example.projetfilrouge_G2.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TicketRestController {

    @Autowired
    TicketRepository ticketRepo;

    @GetMapping("/tickets")
    public List<Ticket> getAll()
    {
        if (ticketRepo.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return ticketRepo.findAll();
    }

    @GetMapping("/tickets/")
    @ResponseBody
    public List<Ticket> getByEvent(@RequestParam String event) {
        if (ticketRepo.findTicketByEventContainingIgnoreCase(event).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ticketRepo.findTicketByEventContainingIgnoreCase(event);
    }
    @GetMapping("/tickets/{id}")
    public Optional<Ticket> getById(@PathVariable Long id) {
        if (ticketRepo.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ticketRepo.findById(id);
    }

    @PostMapping("/tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Ticket ticket) {
        ticketRepo.save(new Ticket(ticket.getDate(),ticket.getEvent(),ticket.getPrice()));
    }

    @PutMapping("/tickets/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@PathVariable Long id, @RequestBody Ticket ticket){
        ticketRepo.save(new Ticket(id,ticket.getEvent(),ticket.getPrice()));
    }

    @DeleteMapping("/tickets/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable Long id) {
        if (ticketRepo.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        ticketRepo.deleteById(id);
    }

    @DeleteMapping("/tickets")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAll() {
        if (ticketRepo.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        ticketRepo.deleteAll();
    }

}