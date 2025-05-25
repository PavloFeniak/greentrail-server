package com.example.userservice.entity.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@Accessors(chain = true)
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "name", nullable = false, length = 30)
    private String name;


    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation = LocalDateTime.now();

    @Column(name = "profile_picture", length = 225)
    private String profilePicture = "";

    @Column(name = "address", length = 100)
    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserParticipation> participations;
}
