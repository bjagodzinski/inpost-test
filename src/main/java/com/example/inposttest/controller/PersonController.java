package com.example.inposttest.controller;

import com.example.inposttest.dto.PersonDto;
import com.example.inposttest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<PersonDto> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

}
