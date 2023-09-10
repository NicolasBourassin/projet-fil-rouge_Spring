package com.example.projetfilrouge_Spring.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="ticket")
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

    //FIXME cardinalités !
//    @OneToOne(mappedBy = "ticket")
//    @Column(name = "transaction")
//    private Transaction transaction;

    //ajouter cardinalité( one to one ou Many to one)
    public Ticket() {}

    public Ticket(Long id, LocalDate date, String event, Float price) {
        this.id = id;
        this.date = date;
        this.event = event;
        this.price = price;
    }

    //FIXME : temporaire juste pour tester code avec transaction sans cardinalité
//    public Ticket(Long id, Transaction transaction) {
//        this.id = id;
//        this.transaction=transaction;
//    }

    public Ticket(LocalDate date, String event, Float price) {
        this.date = date;
        this.event = event;
        this.price = price;
    }


    public Ticket(Long id, String event, Float price) {}

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
