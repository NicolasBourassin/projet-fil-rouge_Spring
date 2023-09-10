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
    public List<Ticket> fetchAll() {
        return ticketRepository.findAll();
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

}
