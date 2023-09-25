package com.example.projetfilrouge_Spring.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "rating")
    private Float rating;

    @OneToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    @JsonIgnoreProperties("transaction")
    private Ticket ticket;
    @ManyToOne
    @JoinColumn(name = "purchaseuser_id")
    @JsonIgnoreProperties("purchaseHistory")
    private User purchaseUser;

    @ManyToOne
    @JoinColumn(name = "sellinguser_id")
    @JsonIgnoreProperties("sellingHistory")
    private User sellingUser;


//    @ManyToOne
//    @JoinColumn(name = "user")
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

    public User getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(User purchase_user) {
        this.purchaseUser = purchase_user;
    }

    public User getSellingUser() {
        return sellingUser;
    }

    public void setSellingUser(User selling_user) {
        this.sellingUser = selling_user;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", completed=" + completed +
                ", date=" + date +
                '}';
    }
}
