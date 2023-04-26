package com.example.inposttest.service;

import com.example.inposttest.dto.PersonDto;
import com.example.inposttest.exception.NotFoundException;
import com.example.inposttest.repository.PersonRepository;
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
