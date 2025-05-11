package com.example.trekservice.repository;

import com.example.trekservice.entity.models.Treks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreksRepository extends JpaRepository<Treks, Long> {

}
