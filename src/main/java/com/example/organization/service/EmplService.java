package com.example.organization.service;

import com.example.organization.dto.EmplDto;

import java.util.Collection;

public interface EmplService {
    EmplDto createEmpl(Long personId, Long orgaId, Collection<Long> roles);
}
