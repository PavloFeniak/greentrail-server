package com.example.userservice.entity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_participation")
public class UserParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "trip_id", nullable = false)
    private int tripId;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Column(name = "status", nullable = false)
    private String satus;
}
