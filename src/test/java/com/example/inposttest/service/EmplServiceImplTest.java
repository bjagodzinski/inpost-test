package com.example.inposttest.service;

import com.example.inposttest.dto.EmplDto;
import com.example.inposttest.exception.EmpAlreadyExistsException;
import com.example.inposttest.exception.NotFoundException;
import com.example.inposttest.model.Empl;
import com.example.inposttest.model.Orga;
import com.example.inposttest.model.Person;
import com.example.inposttest.model.Role;
import com.example.inposttest.repository.EmplRepository;
import com.example.inposttest.repository.OrgaRepository;
import com.example.inposttest.repository.PersonRepository;
import com.example.inposttest.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmplServiceImplTest {

    @Mock
    private EmplRepository emplRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private OrgaRepository orgaRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private EmplServiceImpl emplService;

    @Test
    void shouldThrowExceptionWhenEmplAlreadyExists() {
        // given:
        when(emplRepository.findByPersonIdAndOrgaId(anyLong(), anyLong())).thenReturn(Optional.of(new Empl()));

        // when:
        assertThrows(EmpAlreadyExistsException.class, () -> emplService.createEmpl(1L, 1L, List.of(1L)));
    }

    @Test
    void shouldCreateEmpl() {
        // given:
        when(emplRepository.findByPersonIdAndOrgaId(anyLong(), anyLong())).thenReturn(Optional.empty());
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(new Person()));
        when(orgaRepository.findById(anyLong())).thenReturn(Optional.of(new Orga()));
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(new Role()));
        when(emplRepository.save(any(Empl.class))).thenReturn(new Empl());
        // when:
        final EmplDto empl = emplService.createEmpl(1L, 1L, List.of(1L));

        // then:
        assertNotNull(empl);
    }

    @Test
    void shouldThrowExceptionWhenPersonNotFound() {
        // given:
        when(emplRepository.findByPersonIdAndOrgaId(anyLong(), anyLong())).thenReturn(Optional.empty());
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when:
        assertThrows(NotFoundException.class, () -> emplService.createEmpl(1L, 1L, List.of(1L)));
    }

    @Test
    void shouldThrowExceptionWhenOrgaNotFound() {
        // given:
        when(emplRepository.findByPersonIdAndOrgaId(anyLong(), anyLong())).thenReturn(Optional.empty());
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(new Person()));
        when(orgaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when:
        assertThrows(NotFoundException.class, () -> emplService.createEmpl(1L, 1L, List.of(1L)));
    }

    @Test
    void shouldThrowExceptionWhenRoleNotFound() {
        // given:
        when(emplRepository.findByPersonIdAndOrgaId(anyLong(), anyLong())).thenReturn(Optional.empty());
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(new Person()));
        when(orgaRepository.findById(anyLong())).thenReturn(Optional.of(new Orga()));
        when(roleRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when:
        assertThrows(NotFoundException.class, () -> emplService.createEmpl(1L, 1L, List.of(1L)));
    }

}
