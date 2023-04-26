package com.example.inposttest.dto;

import lombok.Builder;

import java.util.Collection;

@Builder
public record RoleDto(String name,
                      Collection<PermissionDto> permissions) {
}
