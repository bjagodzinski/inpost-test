package com.example.inposttest.controller;

import com.example.inposttest.dto.CreateEmplDto;
import com.example.inposttest.dto.EmplDto;
import com.example.inposttest.dto.PermissionDto;
import com.example.inposttest.dto.RoleDto;
import com.example.inposttest.model.Permission;
import com.example.inposttest.model.Role;
import com.example.inposttest.service.EmplService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmplController.class)
public class EmplControllerTest {

    @MockBean
    private EmplService emplService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateEmpl() throws Exception {
        // Arrange
        long personId = 1;
        long orgaId = 1;
        Role[] roles = {new Role(1L, "roleName", List.of(new Permission(1L, "permissionName")))};
        RoleDto[] roleDtos = {new RoleDto("roleName", List.of(new PermissionDto("permissionName")))};
        EmplDto emplDto = new EmplDto(1L,
                "orgaName",
                "personName",
                "personLastname", Arrays.asList(roleDtos));
        CreateEmplDto createEmplDto = new CreateEmplDto(personId, orgaId, Arrays.stream(roles).map(Role::getId).toArray(Long[]::new));
        when(emplService.createEmpl(personId, orgaId, List.of(1L))).thenReturn(emplDto);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/empl")
                        .content(asJsonString(createEmplDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orgaName").value("orgaName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("personName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("personLastname"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[0]").value(roleDtos[0]));
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonContent = objectMapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
