package com.example.projetfilrouge_G2.repository.entity;

import java.time.LocalDate;

public class Ticket {

    private  Long id;
    private LocalDate date;
    private String event;
    private Float price;

    public Ticket() {}

    public Ticket(Long id, LocalDate date, String event, Float price) {
        this.id = id;
        this.date = date;
        this.event = event;
        this.price = price;
    }

    public Ticket(LocalDate date, String event, Float price) {
        this.date = date;
        this.event = event;
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
        return "Ticket{" +
                "id=" + id +
                ", date=" + date +
                ", event='" + event + '\'' +
                ", price=" + price +
                '}';
    }
}
