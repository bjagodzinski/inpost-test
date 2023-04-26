package com.example.inposttest.service;

import com.example.inposttest.dto.EmplDto;

import java.util.Collection;

public interface EmplService {
    EmplDto createEmpl(Long personId, Long orgaId, Collection<Long> roles);
}
