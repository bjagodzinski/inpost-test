package com.example.inposttest.dto;

import lombok.Builder;

@Builder
public record PersonDto(Long id,
                        String firstName,
                        String lastName) {
}
