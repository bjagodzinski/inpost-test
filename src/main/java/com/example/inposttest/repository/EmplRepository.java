package com.example.inposttest.repository;

import com.example.inposttest.model.Empl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmplRepository extends JpaRepository<Empl, Long> {
    Optional<Empl> findByPersonIdAndOrgaId(Long personId, Long orgaId);
}
