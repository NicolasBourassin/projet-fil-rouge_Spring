package com.example.projetfilrouge_Spring.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
@JsonSerialize(using = TicketSerializer.class)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "eventName")
    private String eventName;

    @Column(name = "eventType")
    private String eventType;

    @Column(name = "eventCity")
    private String eventCity;

    @Column(name = "price")
    private Float price;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    @JsonIgnoreProperties("ticket")
    private Transaction transaction;

    public Ticket() {
    }

    public Ticket(Long id, LocalDate date, String eventName, String eventType, String eventCity, Float price, Transaction transaction) {
        this.id = id;
        this.date = date;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventCity = eventCity;
        this.price = price;
        this.transaction = transaction;
    }

    public Ticket(LocalDate date, String eventName, String eventType, String eventCity, Float price) {
        this.date = date;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventCity = eventCity;
        this.price = price;
    }

    public Ticket(LocalDate date, String eventName, Float price) {
        this.date = date;
        this.eventName = eventName;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
        return "Ticket{" +
                "id=" + id +
                ", date=" + date +
                ", eventName='" + eventName + '\'' +
                ", price=" + price +
                "}";
    }
}