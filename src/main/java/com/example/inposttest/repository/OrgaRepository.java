package com.example.inposttest.repository;

import com.example.inposttest.model.Orga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrgaRepository extends JpaRepository<Orga, Long> {
    Optional<Orga> findByName(String name);
}
