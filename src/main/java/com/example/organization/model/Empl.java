package com.example.organization.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Empl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne
    Person person;

    @ManyToOne
    Orga orga;

    @ManyToMany
    @JoinTable(name = "empl_role",
            joinColumns = { @JoinColumn(name = "empl_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )
    Collection<Role> roles = new ArrayList<>();
}
