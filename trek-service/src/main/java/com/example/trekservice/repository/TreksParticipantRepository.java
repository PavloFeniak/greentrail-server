package com.example.trekservice.repository;

import com.example.trekservice.entity.models.TrekParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreksParticipantRepository extends JpaRepository<TrekParticipants, Long> {
    List<TrekParticipants> findByTrekId(Long id);
    List<TrekParticipants> findByUserId(String id);
}
