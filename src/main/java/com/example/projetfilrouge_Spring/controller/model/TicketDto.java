package com.example.projetfilrouge_Spring.controller.model;

import com.example.projetfilrouge_Spring.repository.entity.Ticket;

import java.time.LocalDate;
import java.util.Optional;

public class TicketDto {
    private  Long id;

    private LocalDate date;

    private String event;

    private Float price;

    public TicketDto(Optional<Ticket> ticket) {
        this.id = ticket.get().getId();
        this.date = ticket.get().getDate();
        this.event = ticket.get().getEvent();
        this.price = ticket.get().getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", date=" + date +
                ", event='" + event + '\'' +
                ", price=" + price +
                '}';
    }
}
