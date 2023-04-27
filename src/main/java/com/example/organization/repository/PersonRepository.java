package com.example.organization.repository;

import com.example.organization.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByFirstName(String firstName);
    Optional<Person> findByLastName(String lastName);
}
