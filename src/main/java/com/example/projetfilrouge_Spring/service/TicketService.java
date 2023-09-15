package com.example.projetfilrouge_Spring.service;

import com.example.projetfilrouge_Spring.controller.model.TicketDto;
import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.TicketRepository;
import com.example.projetfilrouge_Spring.repository.entity.Ticket;
import com.example.projetfilrouge_Spring.repository.entity.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class
TicketService {
    private TicketRepository ticketRepository;
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDto> findAll() {
        List<Ticket> allTickets = ticketRepository.findAll();
        List<TicketDto> allTicketDto = new ArrayList<>();
        for (Ticket ticket: allTickets) {
            TicketDto ticketDto = new TicketDto(Optional.ofNullable(ticket));
            allTicketDto.add(ticketDto);
        }
        return allTicketDto;
    }

    public void save(TicketDto ticketDto) {
        Ticket ticketToAdd = new Ticket(ticketDto.getDate(),
                ticketDto.getEvent(),
                ticketDto.getPrice());
        ticketRepository.save(ticketToAdd);
    }

    public List<TicketDto> findTicketByEventContainingIgnoreCase(String event) {
        List<Ticket> result = ticketRepository.findTicketByEventContainingIgnoreCase(event);
        List<TicketDto> resultTicketDto = new ArrayList<>();
        for (Ticket ticket: result) {
            TicketDto ticketDto = new TicketDto(Optional.ofNullable(ticket));
            resultTicketDto.add(ticketDto);
        }
        return resultTicketDto;
    }

    public Optional<TicketDto> findById(Long id) {
        TicketDto ticketDto = new TicketDto(ticketRepository.findById(id));
        return Optional.of(ticketDto);
    }

    @Transactional
    public void update(Long id, TicketDto ticketDto) {
    // Note : probably not necessary because it's also checked in RestController method
        Ticket ticketToUpdate = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket with id " + id + " not found"));

        // verification for each attribute that a "null" value would not erase the current value
        // temporary variables to avoid confusion between getters & setters
        LocalDate date = ticketDto.getDate();
        String event = ticketDto.getEvent();
        Float price = ticketDto.getPrice();
        if(date!=null){
            ticketToUpdate.setDate(date);
        }
        if(event!=null){
            ticketToUpdate.setEvent(event);
        }
        if(price!=null){
            ticketToUpdate.setPrice(price);
        }
//        System.out.println("TEMP TicketService : ticketDto = " + ticketDto.toString() );
//        System.out.println("TEMP TicketService : ticketToUpdate = " + ticketToUpdate.toString() );
        ticketRepository.save(ticketToUpdate);
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    public TicketDto toDto(Ticket ticket){
        return new TicketDto(Optional.ofNullable(ticket));
    }

    public List<TicketDto> listToDto(List<Ticket> ticketList){
        List<TicketDto> dtoList = new ArrayList<>();
        for (Ticket ticket:ticketList) {
            dtoList.add(toDto(ticket));
        }
        return dtoList;
    }


}
