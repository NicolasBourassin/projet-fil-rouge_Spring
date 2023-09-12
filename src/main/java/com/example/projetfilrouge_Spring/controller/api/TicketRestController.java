package com.example.projetfilrouge_Spring.controller.api;

import com.example.projetfilrouge_Spring.controller.model.TicketDto;
import com.example.projetfilrouge_Spring.service.TicketService;
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

    TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    public List<TicketDto> getAll()
    {
        if (ticketService.fetchAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return ticketService.fetchAll();
    }

    @GetMapping("/tickets/")
    @ResponseBody
    public List<TicketDto> getByEvent(@RequestParam String event) {
        if (ticketService.findTicketByEventContainingIgnoreCase(event).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ticketService.findTicketByEventContainingIgnoreCase(event);
    }
    @GetMapping("/tickets/{id}")
    public Optional<TicketDto> getById(@PathVariable Long id) {
        if (ticketService.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ticketService.findById(id);
    }

    @PostMapping("/tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody TicketDto ticketDto) {
        ticketService.save(ticketDto);
    }

    @PutMapping("/tickets/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@PathVariable Long id, @RequestBody TicketDto ticketDto){
        ticketService.save(ticketDto);
    }

    @DeleteMapping("/tickets/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable Long id) {
        if (ticketService.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        ticketService.deleteById(id);
    }

}