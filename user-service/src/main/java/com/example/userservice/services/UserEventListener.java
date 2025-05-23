package com.example.userservice.services;

import com.example.userservice.entity.DTO.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventListener {

    private final UserService userService;
    @RabbitListener(queues = "user.registered")
    public void handleUserRegistered(UserRequestDTO event) {
        UserRequestDTO newUser = new UserRequestDTO()
                .setEmail(event.getEmail())
                .setName(event.getName())
                .setPhoneNumber(event.getPhoneNumber())
                .setAddress(event.getAddress());

        userService.addUser(newUser);
    }

}
