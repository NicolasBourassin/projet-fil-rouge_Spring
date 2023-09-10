package com.example.projetfilrouge_Spring.repository.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phoneNumber")
    private Long phoneNumber;
    @Column(name="photoUrl")
    private String photoUrl;
    @Column(name="email")
    private String email;

////  @OneToMany(mappedBy = "user")
//    private List<Transaction> purchaseHistory;
//////@OneToMany(mappedBy = "user")
//    private List<Transaction> sellingHistory;

    public User() {}

    public User(Long id, String username, String password, Long phoneNumber, String photoUrl, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.email = email;
    }

    public User(String username, String password, Long phoneNumber, String photoUrl, String email, List<Transaction> purchaseHistory, List<Transaction> sellingHistory)
    {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.email = email;
//        this.purchaseHistory = purchaseHistory;
//        this.sellingHistory = sellingHistory;
    }

    public User(String username, String password, Long phoneNumber, String photoUrl, String email) {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public Long getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(Long phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getPhotoUrl() {return photoUrl;}

    public void setPhotoUrl(String photoUrl) {this.photoUrl = photoUrl;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

//    public List<Transaction> getPurchaseHistory() {return purchaseHistory;}
//
//    public void setPurchaseHistory(List<Transaction> purchaseHistory) {this.purchaseHistory = purchaseHistory;}
//
//    public List<Transaction> getSellingHistory() {return sellingHistory;}
//
//    public void setSellingHistory(List<Transaction> sellingHistory) {this.sellingHistory = sellingHistory;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", photoUrl='" + photoUrl + '\'' +
                ", email='" + email + '\'' +
//                ", purchaseHistory=" + purchaseHistory +
//                ", sellingHistory=" + sellingHistory +
                '}';
    }
}
