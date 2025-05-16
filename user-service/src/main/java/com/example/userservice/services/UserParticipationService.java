package com.example.userservice.services;

import com.example.userservice.entity.DTO.UserParticipationDTO;
import com.example.userservice.entity.DTO.UserParticipationRequestDTO;
import com.example.userservice.entity.model.UserParticipation;
import com.example.userservice.entity.model.Users;
import com.example.userservice.repository.UserParticipationRepository;
import com.example.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserParticipationService {

    private final UserParticipationRepository userParticipationRepository;
    private final UserRepository userRepository;

    public List<UserParticipationDTO> getParticipationForUser(Long userId) {
        List<UserParticipation> participation = userParticipationRepository.findByUserId(userId);
        return participation.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public UserParticipationDTO joinTrip(Long userId, UserParticipationRequestDTO request) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (userParticipationRepository.findByUserIdAndTripId(userId, request.getTripId()).isPresent()) {
            throw new RuntimeException("Already joined");
        }
        UserParticipation userParticipation = new UserParticipation()
                .setUser(user)
                .setTripId(request.getTripId())
                .setSatus(request.getStatus());
        userParticipationRepository.save(userParticipation);
        return mapToDto(userParticipation);
    }

    public void leaveTrip(Long userId, int tripId) {
       UserParticipation participation = userParticipationRepository.findByUserIdAndTripId(userId, tripId)
               .orElseThrow(() -> new RuntimeException("User not found"));
       userParticipationRepository.delete(participation);
    }
    public UserParticipationDTO updateParticipationStatus(Long userId, int tripId, String status) {
        UserParticipation participation = userParticipationRepository.findByUserIdAndTripId(userId, tripId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        participation.setSatus(status);
        userParticipationRepository.save(participation);
        return mapToDto(participation);
    }



    public UserParticipationDTO mapToDto(UserParticipation userParticipation) {
        return new UserParticipationDTO()
                .setId(userParticipation.getId())
                .setUserId(userParticipation.getUser().getId())
                .setTripId(userParticipation.getTripId())
                .setJoinedAt(userParticipation.getJoinedAt())
                .setStatus(userParticipation.getSatus());
    }
}
