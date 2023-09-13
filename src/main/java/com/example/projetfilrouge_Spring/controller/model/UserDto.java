package com.example.projetfilrouge_Spring.controller.model;

import com.example.projetfilrouge_Spring.repository.entity.Role;
import com.example.projetfilrouge_Spring.repository.entity.Transaction;
import com.example.projetfilrouge_Spring.repository.entity.User;

import java.util.List;
import java.util.Optional;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private String photoUrl;
    private String email;
    private List<Role> roleList;
    private List<Transaction> purchaseHistory;
    private List<Transaction> sellingHistory;


    public UserDto(Optional<User> user) {
        this.id = user.get().getId();
        this.username = user.get().getUsername();
        this.password = user.get().getPassword();
        this.phoneNumber = user.get().getPhoneNumber();
        this.photoUrl = user.get().getPhotoUrl();
        this.email = user.get().getEmail();
        this.roleList = user.get().getRoleList();
        this.purchaseHistory = user.get().getPurchaseHistory();
        this.sellingHistory = user.get().getSellingHistory();
    }

    public UserDto(Long id, String username, String password, String phoneNumber, String photoUrl, String email,
                   List<Role> roleList, List<Transaction> purchaseHistory, List<Transaction> sellingHistory) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.email = email;
        this.roleList = roleList;
        this.purchaseHistory = purchaseHistory;
        this.sellingHistory = sellingHistory;
    }

    public UserDto(String username, String password, String phoneNumber, String photoUrl, String email,
                 List<Role> roleList, List<Transaction> purchaseHistory, List<Transaction> sellingHistory) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.email = email;
        this.roleList = roleList;
        this.purchaseHistory = purchaseHistory;
        this.sellingHistory = sellingHistory;
    }

    public UserDto(String username, String password, String phoneNumber, String photoUrl, String email) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Transaction> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(List<Transaction> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public List<Transaction> getSellingHistory() {
        return sellingHistory;
    }

    public void setSellingHistory(List<Transaction> sellingHistory) {
        this.sellingHistory = sellingHistory;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", photoUrl='" + photoUrl + '\'' +
                ", email='" + email + '\'' +
                ", purchaseHistory=" + purchaseHistory +
                ", sellingHistory=" + sellingHistory +
                '}';
    }
}
