package com.example.projetfilrouge_Spring.controller.model;

import com.example.projetfilrouge_Spring.repository.entity.Ticket;

import java.time.LocalDate;
import java.util.Optional;

public class TicketDto {
    private  Long id;
    private LocalDate date;
    private String eventName;
    private String eventType;
    private String eventCity;
    private Float price;

    public TicketDto() {
    }

    public TicketDto(Optional<Ticket> ticket) {
        this.id = ticket.get().getId();
        this.date = ticket.get().getDate();
        this.eventName = ticket.get().getEventName();
        this.eventCity = ticket.get().getEventCity();
        this.eventType = ticket.get().getEventType();
        this.price = ticket.get().getPrice();
    }

    public TicketDto(Long id, LocalDate date, String eventName, String eventType, String eventCity, Float price) {
        this.id = id;
        this.date = date;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventCity = eventCity;
        this.price = price;
    }

    public TicketDto(LocalDate date, String eventName, String eventType, String eventCity, Float price) {
        this.date = date;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventCity = eventCity;
        this.price = price;
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventCity() {
        return eventCity;
    }

    public void setEventCity(String eventCity) {
        this.eventCity = eventCity;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", date=" + date +
                ", event='" + eventName + '\'' +
                ", price=" + price +
                '}';
    }
}
