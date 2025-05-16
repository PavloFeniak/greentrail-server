package com.example.authservice.repository;

import com.example.authservice.entity.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokensRepository extends JpaRepository<RefreshToken, Long> {
}
