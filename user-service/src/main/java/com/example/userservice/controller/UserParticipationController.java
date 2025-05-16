package com.example.userservice.controller;

import com.example.userservice.entity.DTO.UserParticipationDTO;
import com.example.userservice.entity.DTO.UserParticipationRequestDTO;
import com.example.userservice.entity.model.UserParticipation;
import com.example.userservice.services.UserParticipationService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/participation")
@RequiredArgsConstructor
public class UserParticipationController {

    private final UserParticipationService userParticipationService;
    private final UserService userService;

    @GetMapping
    public List<UserParticipationDTO> getUserParticipation(@PathVariable Long userId) {
        return userParticipationService.getParticipationForUser(userId);
    }

    @PostMapping
    public UserParticipationDTO joinTrip(@PathVariable Long userId, @RequestBody UserParticipationRequestDTO userParticipationDTO) {
        return userParticipationService.joinTrip(userId, userParticipationDTO);
    }

    @DeleteMapping("/{tripId}")
    public void leaveTrip(@PathVariable Long userId, @PathVariable int tripId) {
        userParticipationService.leaveTrip(userId, tripId);
    }

    @PutMapping("/{tripId}")
    public UserParticipationDTO updateStatus(@PathVariable Long userId,
                                             @PathVariable int tripId,
                                             @RequestParam String status) {
        return userParticipationService.updateParticipationStatus(userId, tripId, status);
    }
}
