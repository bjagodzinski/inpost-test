package com.example.organization.dto;

import lombok.Builder;

@Builder
public record PersonDto(Long id,
                        String firstName,
                        String lastName) {
}
