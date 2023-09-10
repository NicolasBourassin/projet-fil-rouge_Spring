package com.example.projetfilrouge_G2.service;

import com.example.projetfilrouge_G2.controller.model.TicketDto;
import com.example.projetfilrouge_G2.repository.TicketRepository;
import com.example.projetfilrouge_G2.repository.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDto> fetchAll() {
        List<Ticket> allTickets = ticketRepository.findAll();
        List<TicketDto> allTicketDto = null;
        for (Ticket ticket: allTickets) {
            TicketDto ticketDto = new TicketDto(Optional.ofNullable(ticket));
            allTicketDto.add(ticketDto);
        }
        return allTicketDto;
    }

    public TicketDto fetchById(Long id) throws Exception {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        TicketDto ticketDto = new TicketDto(ticket);
        return ticketDto;
    }

    public void save(TicketDto ticketDto) {
        Ticket ticketToAdd = new Ticket(ticketDto.getDate(),
                ticketDto.getEvent(),
                ticketDto.getPrice());
        ticketRepository.save(ticketToAdd);
    }

    public void remove(Long id) {
        ticketRepository.deleteById(id);
    }


    public List<TicketDto> findTicketByEventContainingIgnoreCase(String event) {
        return ticketRepository.findTicketByEventContainingIgnoreCase(event);
    }

    public Optional<TicketDto> findById(Long id) {
        TicketDto ticketDto = new TicketDto(ticketRepository.findById(id));
        return Optional.of(ticketDto);
    }


    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
