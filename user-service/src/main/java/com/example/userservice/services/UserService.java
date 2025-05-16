package com.example.userservice.services;

import com.example.userservice.entity.DTO.UserResponseDTO;
import com.example.userservice.entity.DTO.UserUpdateDTO;
import com.example.userservice.entity.model.Users;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO getUserById(Long id) {
        Users user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    public UserResponseDTO getUserByEmail(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userUpdateDTO.getName());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        user.setProfilePicture(userUpdateDTO.getProfilePicture());
        user.setAddress(userUpdateDTO.getAddress());
        userRepository.save(user);
        return mapToDTO(user);
    }
    public UserResponseDTO addUser(UserResponseDTO userResponseDTO) {
        if(userRepository.findByEmail(userResponseDTO.getEmail()).isPresent()){
            throw new RuntimeException("User already exists");
        }
        Users user = new Users().setEmail(userResponseDTO.getEmail())
                .setPhoneNumber(userResponseDTO.getPhoneNumber())
                .setName(userResponseDTO.getName())
                .setProfilePicture(userResponseDTO.getProfilePicture())
                .setAddress(userResponseDTO.getAddress());
        userRepository.save(user);
        return mapToDTO(user);
    }

    private UserResponseDTO mapToDTO(Users user) {
        return new UserResponseDTO().setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPhoneNumber(user.getPhoneNumber())
                .setProfilePicture(user.getProfilePicture())
                .setAddress(user.getAddress());
    }
}
