package com.islamlucas.projetantigaspi.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.management.relation.Role;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
public class RegistrationRequest {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
