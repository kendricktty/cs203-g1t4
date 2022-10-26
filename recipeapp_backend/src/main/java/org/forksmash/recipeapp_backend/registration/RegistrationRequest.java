package org.forksmash.recipeapp_backend.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    //add in other credentials here later on
    private final int age;
    private final String sex;
    private final double heightCM;
    private final double weightKG;
}
