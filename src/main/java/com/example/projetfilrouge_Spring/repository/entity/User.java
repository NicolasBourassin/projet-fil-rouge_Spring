package com.example.projetfilrouge_Spring.repository.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="username",unique = true)
    private String username;
    @Column(name = "password",unique = true)
    private String password;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name="photoUrl")
    private String photoUrl;
    @Column(name="email")
    private String email;
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<Role> roleList;

    @OneToMany(mappedBy = "user")
    private List<Transaction> purchaseHistory;
    @OneToMany(mappedBy = "user")
    private List<Transaction> sellingHistory;

    public User() {}

    public User(String username, String password, String phoneNumber, String photoUrl, String email, List<Role> roleList, List<Transaction> purchaseHistory, List<Transaction> sellingHistory) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.email = email;
        this.roleList = roleList;
        this.purchaseHistory = purchaseHistory;
        this.sellingHistory = sellingHistory;
    }

    public User(Long id, String username, String password, String phoneNumber, String photoUrl, String email,
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

    public User(String username, String password, String phoneNumber, String photoUrl, String email) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Role> getRoleList() {return roleList;}

    public void setRoleList(List<Role> roleList) {this.roleList = roleList;}

    public String getPhotoUrl() {return photoUrl;}

    public void setPhotoUrl(String photoUrl) {this.photoUrl = photoUrl;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public List<Transaction> getPurchaseHistory() {return purchaseHistory;}

    public void setPurchaseHistory(List<Transaction> purchaseHistory) {this.purchaseHistory = purchaseHistory;}

    public List<Transaction> getSellingHistory() {return sellingHistory;}

    public void setSellingHistory(List<Transaction> sellingHistory) {this.sellingHistory = sellingHistory;}
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", email='" + email + '\'' +
                ", roleList=" + roleList +
                ", purchaseHistory=" + purchaseHistory +
                ", sellingHistory=" + sellingHistory +
                '}';
    }
}
