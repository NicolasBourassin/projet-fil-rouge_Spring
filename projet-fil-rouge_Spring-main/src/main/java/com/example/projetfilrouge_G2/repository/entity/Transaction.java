package com.example.projetfilrouge_G2.repository.entity;

import java.time.LocalDate;

public class Transaction {

    private Long id;

    private Boolean completed;

    private Ticket ticket;

    private LocalDate date;

    public Transaction() {}

    public Transaction(Boolean completed, Ticket ticket, LocalDate date) {
        this.completed = completed;
        this.ticket = ticket;
        this.date = date;
    }

    public Boolean getCompleted() {return completed;}

    public void setCompleted(Boolean completed) {this.completed = completed;}

    public Ticket getTicket() {return ticket;}

    public void setTicket(Ticket ticket) {this.ticket = ticket;}

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", completed=" + completed +
                ", ticket=" + ticket +
                ", date=" + date +
                '}';
    }
}
