package com.example.projetfilrouge_G2.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name ="date")
    private LocalDate date;

    @Column(name ="event")
    private String event;

    @Column(name = "price")
    private Float price;

    //ajouter cardinalité( one to one ou Many to one
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
