package com.example.projetfilrouge_Spring.controller.model;

import com.example.projetfilrouge_Spring.repository.entity.Transaction;
import com.example.projetfilrouge_Spring.repository.entity.User;

import java.util.List;
import java.util.Optional;

public class UserDto {
    private Long id;

    private String username;

    private String password;

    private Long phoneNumber;

    private String photoUrl;

    private String email;

    private List<Transaction> purchaseHistory;

    private List<Transaction> sellingHistory;

    private User user;
    public UserDto(Optional<User> user) {
        //FIXME constructeur temporaire, à supprimer si conflit !
    }

    public UserDto(Long id, String username, String password, Long phoneNumber, String photoUrl, String email, List<Transaction> purchaseHistory, List<Transaction> sellingHistory) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.photoUrl = user.getPhotoUrl();
        this.email = user.getEmail();
//        this.purchaseHistory = user.getPurchaseHistory();
//        this.sellingHistory = user.getSellingHistory();
    }

    public UserDto(String username, String password, Long phoneNumber, String photoUrl, String email, List<Transaction> purchaseHistory, List<Transaction> sellingHistory) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.photoUrl = user.getPhotoUrl();
        this.email = user.getEmail();
//        this.purchaseHistory = user.getPurchaseHistory();
//        this.sellingHistory = user.getSellingHistory();
    }

    public UserDto(String username, String password, Long phoneNumber, String photoUrl, String email) {}

    public UserDto(Long id, String username, String password, Long phoneNumber, String photoUrl, String email) {}

    public Long getId() {
        return user.getId();
    }

    public void setId(Long id) {this.id = id;}

    public String getUsername() {
        return user.getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return user.getPhoneNumber();
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoUrl() {
        return user.getPhotoUrl();
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<Transaction> getPurchaseHistory() {
//        return user.getPurchaseHistory();
//    }
//
//    public void setPurchaseHistory(List<Transaction> purchaseHistory) {
//        this.purchaseHistory = purchaseHistory;
//    }
//
//    public List<Transaction> getSellingHistory() {return user.getSellingHistory();}
//
//    public void setSellingHistory(List<Transaction> sellingHistory) {
//        this.sellingHistory = sellingHistory;
//    }

    @Override
    public String toString() {
        return "UserDto{" +
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
