package com.example.inposttest.service;

import com.example.inposttest.dto.PersonDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> getPersons();
    PersonDto getPerson(Long id);
}
