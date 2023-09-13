package com.example.projetfilrouge_Spring.controller.model;

import com.example.projetfilrouge_Spring.repository.entity.Role;
import com.example.projetfilrouge_Spring.repository.entity.Transaction;
import com.example.projetfilrouge_Spring.repository.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserDto implements UserDetails {
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
    }

    public UserDto(Long id, String username, String password, String phoneNumber, String photoUrl, String email, List<Transaction> purchaseHistory, List<Transaction> sellingHistory) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.email = email;
        this.purchaseHistory = purchaseHistory;
        this.sellingHistory = sellingHistory;
    }

    public UserDto(String username, String password, String phoneNumber, String photoUrl, String email, List<Transaction> purchaseHistory, List<Transaction> sellingHistory) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.email = email;
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

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        //boucler sur notre liste de roles ci-dessus

//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : this.roleList){
//            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
//        }

        this.roleList
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                .toList();

        //Dans la boucle, créer un new SimpleGrantedAuthority grace au nom du role
        //Ajouter ce SimpleGrantedAuthority dans une liste
        //retourner cette liste de SimpleGrantedAuthority
        return null;}


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
