package com.example.authservice.controller;

import com.example.authservice.entity.DTO.AuthRequest;
import com.example.authservice.entity.DTO.AuthResponse;
import com.example.authservice.entity.DTO.LoginRequestDTO;
import com.example.authservice.entity.DTO.UserRegisteredEvent;
import com.example.authservice.entity.model.Users;
import com.example.authservice.jwt.JwtUtil;
import com.example.authservice.repository.UsersRepository;
import com.example.authservice.service.UserEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UsersController {
    @PostMapping
    public Users getUser(@RequestBody Users user) {
        return user;
    }

    @GetMapping("/hello")
    public String hello() {return "hello";}
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserEventPublisher eventPublisher;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authUser) {
        if (userRepository.findByEmail(authUser.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is taken");
        }

        Users user = new Users().setEmail(authUser.getEmail()).setPasswordHash(passwordEncoder.encode(authUser.getPassword()));
        userRepository.save(user);
        eventPublisher.sendUserRegisteredEvent(
                new UserRegisteredEvent(
                        authUser.getEmail(),
                        authUser.getName(),
                        authUser.getPhoneNumber(),
                        authUser.getAddress()
                )
        );

        return ResponseEntity.ok("User registered");

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO authRequest) {
        try {
            authenticationManager.authenticate( 
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }

        Users user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),  // або getUsername(), якщо є
                user.getPasswordHash(),
                List.of() // без ролей
        );


        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
