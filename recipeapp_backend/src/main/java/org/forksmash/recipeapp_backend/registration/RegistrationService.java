package org.forksmash.recipeapp_backend.registration;

import org.forksmash.recipeapp_backend.appuser.AppUser;
import org.forksmash.recipeapp_backend.appuser.AppUserRole;
import org.forksmash.recipeapp_backend.appuser.AppUserService;
import org.springframework.stereotype.Service;
import lombok.*;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail)
            throw new IllegalStateException("Email " + request.getEmail() + " is not valid");
        return appUserService.signUpUser(
            new AppUser(
                request.getFirstName(), 
                request.getLastName(), 
                request.getEmail(), 
                request.getPassword(),
                AppUserRole.USER
            )
        );
    }
}
