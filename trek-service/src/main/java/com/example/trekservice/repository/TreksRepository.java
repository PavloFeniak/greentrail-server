package com.example.trekservice.repository;

import com.example.trekservice.entity.models.Treks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreksRepository extends JpaRepository<Treks, Long> {
    List<Treks> findByCreatedBy(String createdBy);
}
