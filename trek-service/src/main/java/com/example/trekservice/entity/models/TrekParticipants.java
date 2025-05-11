package com.example.trekservice.entity.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "trek_participants")
public class TrekParticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trip_id", nullable = false)
    private Treks trek;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Column(name = "status")
    private String status;
}
