package org.forksmash.recipeapp_backend.registration;

import org.forksmash.recipeapp_backend.appuser.AppUser;
import org.forksmash.recipeapp_backend.appuser.AppUserService;
import org.forksmash.recipeapp_backend.appuser.AppUserRole;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService AppUserService;
    // private final EmailValidator emailValidator;
    // private final ConfirmationTokenService confirmationTokenService;
    // private final EmailSender emailSender;


    public String register(RegistrationRequest request) {

        String token = AppUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER

                ));

        // String link = "http://localhost:8080/api/v1/registration/confirm?token=" +
        // token;
        // emailSender.send(
        // request.getEmail(),
        // buildEmail(request.getFirstName(), link));

        return token;
    }
}