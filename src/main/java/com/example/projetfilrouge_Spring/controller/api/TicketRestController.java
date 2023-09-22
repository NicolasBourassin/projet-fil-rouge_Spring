package com.example.projetfilrouge_Spring.controller.api;

import com.example.projetfilrouge_Spring.controller.model.TicketDto;
import com.example.projetfilrouge_Spring.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        if (ticketService.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return ticketService.findAll();
    }

    @GetMapping("/tickets?")
    @ResponseBody
    public List<TicketDto> getByEventName(@RequestParam String eventName) {
        if (ticketService.findTicketByEventNameContainingIgnoreCase(eventName).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ticketService.findTicketByEventNameContainingIgnoreCase(eventName);
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
    public ResponseEntity<TicketDto> updateById(@PathVariable Long id, @RequestBody TicketDto ticketDto){
        Optional<TicketDto> updateTarget = ticketService.findById(id);
        if (updateTarget.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        TicketDto updateVersion = updateTarget.get();

        updateVersion.setDate(ticketDto.getDate());
        updateVersion.setEventName(ticketDto.getEventName());
        updateVersion.setPrice(ticketDto.getPrice());

        //TODO TEMP
        System.out.println("TEMP TicketRestController : TicketDto = " + updateVersion.toString() );
        ticketService.update(id, updateVersion);
        return ResponseEntity.status(HttpStatus.OK).body(updateVersion);
    }


    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        Optional<TicketDto> ticketDto = ticketService.findById(id);
        if (ticketDto.isPresent()){
            ticketService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}