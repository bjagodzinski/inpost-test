package com.example.organization.repository;

import com.example.organization.model.Orga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrgaRepository extends JpaRepository<Orga, Long> {
    Optional<Orga> findByName(String name);
}
