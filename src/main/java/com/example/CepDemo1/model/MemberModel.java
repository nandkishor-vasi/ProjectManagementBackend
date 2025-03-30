package com.example.CepDemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
public class MemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @OneToOne(cascade = CascadeType.ALL)
   @MapsId
   @JoinColumn(name = "user_id", referencedColumnName = "id")
   @JsonIgnoreProperties({"members"})
   private UserModel user;

    @Column(nullable = true)
    private String profilePicture; // URL or path to the profile image

    @Column(nullable = true)
    private String address;


    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

//    @ManyToMany(mappedBy = "assignedUsers")
//    private Set<Project> assignedProjects = new HashSet<>();
}
