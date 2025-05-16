package com.example.userservice.repository;

import com.example.userservice.entity.model.UserParticipation;
import com.example.userservice.entity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserParticipationRepository extends JpaRepository<UserParticipation, Long> {
    List<UserParticipation> findByUserId(Long userId);
    Optional<UserParticipation> findByUserIdAndTripId(Long userId, int tripId);

}
