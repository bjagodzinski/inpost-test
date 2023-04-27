package com.example.organization.service;

import com.example.organization.dto.PersonDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> getPersons();
    PersonDto getPerson(Long id);
}
