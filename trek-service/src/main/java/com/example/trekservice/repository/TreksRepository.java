package com.example.trekservice.repository;

import com.example.trekservice.entity.models.Treks;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreksRepository extends JpaRepository<Treks, Long> {
    List<Treks> findByCreatedBy(String createdBy);
    default List<Treks> findTopN(int n) {
        return findAll(PageRequest.of(0, n)).getContent();
    }
}
