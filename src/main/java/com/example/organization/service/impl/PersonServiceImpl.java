package com.example.organization.service.impl;

import com.example.organization.dto.PersonDto;
import com.example.organization.exception.NotFoundException;
import com.example.organization.repository.PersonRepository;
import com.example.organization.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<PersonDto> getPersons() {
        return personRepository.findAll().stream()
                .map(person -> PersonDto.builder()
                        .id(person.getId())
                        .firstName(person.getFirstName())
                        .lastName(person.getLastName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getPerson(Long id) {
        return personRepository.findById(id)
                .map(person -> PersonDto.builder()
                        .id(person.getId())
                        .firstName(person.getFirstName())
                        .lastName(person.getLastName())
                        .build())
                .orElseThrow(NotFoundException.getExceptionSupplier(id));
    }

}
