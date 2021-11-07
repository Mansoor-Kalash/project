package com.dammak.project401.models;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Hospital implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String placeName;
    private String password;
    @ManyToMany
    @JoinTable(
            name="donors_hospitals",
            joinColumns = { @JoinColumn(name = "hospital_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    public List<AppUser> donors;


    public Hospital() {
    }

    public Hospital(String username,String password, String placeName) {
        this.username = username;
        this.password=password;
        this.placeName = placeName;

    }

    public Long getId() {
        return id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AppUser> getDonors() {
        return donors;
    }

    public void setDonors(List<AppUser> donors) {
        this.donors = donors;
    }
    public void addDonor(AppUser donor){
        getDonors().add(donor);
    }
}
