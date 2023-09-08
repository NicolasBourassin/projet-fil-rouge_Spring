package com.example.projetfilrouge_G2.repository.entity;

import java.time.LocalDate;

public class Ticket {

    private  Long id;

    private LocalDate date;

    private String event;

    public Ticket() {}

    public Ticket(LocalDate date, String event) {
        this.date = date;
        this.event = event;
    }

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public String getEvent() {return event;}

    public void setEvent(String event) {this.event = event;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", date=" + date +
                ", event='" + event + '\'' +
                '}';
    }
}
