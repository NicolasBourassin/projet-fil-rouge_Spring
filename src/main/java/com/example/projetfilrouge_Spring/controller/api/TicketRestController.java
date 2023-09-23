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

//    // TODO : idem avec getByEventCity & getByEventType
    // // fixme : probably redundant with next multi-parameter search method, but maybe still faster to run ?
//    @GetMapping("/tickets/event")
//    @ResponseBody
//    public List<TicketDto> getByEventName(@RequestParam("eventName") String eventName) {
//        if (ticketService.findTicketByEventNameContainingIgnoreCase(eventName).isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return ticketService.findTicketByEventNameContainingIgnoreCase(eventName);
//    }


    // Multiple parameter searching method :
    // - eventName : partial matches accepted (upper & lowercase tolerated)
    // - eventCity & eventType : perfect matches only (but upper & lowercase still tolerated)
    //
    // Example request url : http://localhost:8080/api/tickets/search?eventCity=paris&eventType=festival&eventName=ol
    // will return all festivals at Paris with eventName containing "ol".
    //
    // Note : an empty parameter is considered as a catch-all value, so :
    // http://localhost:8080/api/tickets/search?eventType=festival will return all festivals.
    @GetMapping("/tickets/search")
    @ResponseBody
    public List<TicketDto> searchTickets(
            @RequestParam(name = "eventName", required = false) String eventName,
            @RequestParam(name = "eventCity", required = false) String eventCity,
            @RequestParam(name = "eventType", required = false) String eventType) {

        // Use your service method to perform the search with DTOs
        return ticketService.searchTickets(eventName, eventCity, eventType);
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
        updateVersion.setEventType(ticketDto.getEventType());
        updateVersion.setEventCity(ticketDto.getEventCity());
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