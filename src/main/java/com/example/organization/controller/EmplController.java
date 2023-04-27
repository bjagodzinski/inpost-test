package com.example.organization.controller;

import com.example.organization.dto.CreateEmplDto;
import com.example.organization.dto.EmplDto;
import com.example.organization.service.EmplService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/empls")
@RequiredArgsConstructor
public class EmplController {

    private final EmplService emplService;

    @PostMapping
    public EmplDto createEmpl(@RequestBody CreateEmplDto createEmplDto) {
        return emplService.createEmpl(createEmplDto.personId(), createEmplDto.orgaId(), Arrays.asList(createEmplDto.roles()));
    }

}
