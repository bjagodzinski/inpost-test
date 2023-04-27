package com.example.organization.dto;

import lombok.Builder;

import java.util.Collection;

@Builder
public record EmplDto(Long id,
                      String orgaName,
                      String firstName,
                      String lastName,
                      Collection<RoleDto> roles) {
}
