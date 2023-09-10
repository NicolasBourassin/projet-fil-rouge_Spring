package com.example.projetfilrouge_G2.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="completed")
    private Boolean completed;

    @Column(name ="ticket")
    private Ticket ticket;
//    @OneToOne(mappedBy = "transaction")
//    private Ticket ticket;

    @Column(name ="date")
    private LocalDate date;

    @Column(name ="rating")
    private Float rating;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    public Transaction() {}

    public Transaction(Boolean completed, Ticket ticket, LocalDate date) {
        this.completed = completed;
        this.ticket = ticket;
        this.date = date;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
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
//                ", ticket=" + ticket +
                ", date=" + date +
                ", rating=" + rating +
                '}';
    }
}
