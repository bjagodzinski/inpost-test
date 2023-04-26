package com.example.inposttest.service;

import com.example.inposttest.dto.EmplDto;
import com.example.inposttest.dto.PermissionDto;
import com.example.inposttest.dto.RoleDto;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmplServiceImpl implements EmplService {

    private final EmplRepository emplRepository;
    private final PersonRepository personRepository;
    private final OrgaRepository orgaRepository;
    private final RoleRepository roleRepository;

    @Override
    public EmplDto createEmpl(Long personId,
                              Long orgaId,
                              Collection<Long> roles) {

        emplRepository.findByPersonIdAndOrgaId(personId, orgaId).ifPresent(empl -> {
            throw new EmpAlreadyExistsException();
        });

        final Person person = personRepository.findById(personId).orElseThrow(NotFoundException.getExceptionSupplier(personId));
        final Orga orga = orgaRepository.findById(orgaId).orElseThrow(NotFoundException.getExceptionSupplier(orgaId));

        final Empl empl = new Empl();
        empl.setPerson(person);
        empl.setOrga(orga);
        emplRepository.save(empl);
        roles.forEach(roleId -> {
            final Role role = roleRepository.findById(roleId).orElseThrow(NotFoundException.getExceptionSupplier(roleId));
            empl.getRoles().add(role);
        });
        emplRepository.save(empl);

        return EmplDto.builder()
                .id(empl.getId())
                .firstName(person.getFirstName())
                .orgaName(orga.getName())
                .roles(empl.getRoles().stream().map(role -> RoleDto.builder()
                        .name(role.getName())
                        .permissions(role.getPermissions().stream()
                                .map(permission -> PermissionDto.builder().name(permission.getName()).build())
                                .collect(Collectors.toList())).build()
                ).collect(Collectors.toList()))
                .build();
    }

}
