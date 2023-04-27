package com.example.organization.controller;

import com.example.organization.dto.CreateEmplDto;
import com.example.organization.dto.EmplDto;
import com.example.organization.dto.PermissionDto;
import com.example.organization.dto.RoleDto;
import com.example.organization.model.Permission;
import com.example.organization.model.Role;
import com.example.organization.service.EmplService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmplController.class)
public class EmplControllerTest {

    public static final String ROLE_NAME = "roleName";
    public static final String PERMISSION_NAME = "permissionName";
    public static final String ORGA_NAME = "orgaName";
    public static final String PERSON_NAME = "personName";
    public static final String PERSON_LASTNAME = "personLastname";
    @MockBean
    private EmplService emplService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateEmpl() throws Exception {
        // Arrange
        long personId = 1;
        long orgaId = 1;
        CreateEmplDto createEmplDto = getCreateEmplDto(personId, orgaId);
        LinkedHashMap<String, Object> expectedRolesAndPermissions = getExpectedRolesAndPermissions();
        
        EmplDto emplDto = getEmplDto();
        when(emplService.createEmpl(personId, orgaId, List.of(1L))).thenReturn(emplDto);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/empls")
                        .content(asJsonString(createEmplDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orgaName").value(ORGA_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(PERSON_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(PERSON_LASTNAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[0]").value(expectedRolesAndPermissions));
    }

    private static CreateEmplDto getCreateEmplDto(long personId, long orgaId) {
        List<Role> roles = List.of(new Role(1L, ROLE_NAME, List.of(new Permission(1L, PERMISSION_NAME))));
        CreateEmplDto createEmplDto = new CreateEmplDto(personId, orgaId, roles.stream().map(Role::getId).toArray(Long[]::new));
        return createEmplDto;
    }

    private static EmplDto getEmplDto() {
        List<RoleDto> roleDtos = List.of(new RoleDto(ROLE_NAME, List.of(new PermissionDto(PERMISSION_NAME))));
        EmplDto emplDto = new EmplDto(1L,
                ORGA_NAME,
                PERSON_NAME,
                PERSON_LASTNAME,
                roleDtos);
        return emplDto;
    }

    private static LinkedHashMap<String, Object> getExpectedRolesAndPermissions() {
        LinkedHashMap<String, Object> permissionDtos = new LinkedHashMap<>();
        permissionDtos.put("name", PERMISSION_NAME);
        LinkedHashMap<String, Object> roleDto = new LinkedHashMap<>();
        roleDto.put("name", "roleName");
        roleDto.put("permissions", List.of(permissionDtos));
        return roleDto;
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
