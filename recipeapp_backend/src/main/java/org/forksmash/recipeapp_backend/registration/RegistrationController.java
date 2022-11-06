package org.forksmash.recipeapp_backend.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public void register(@RequestBody RegistrationRequest request) {
        //return
        log.info("andoawnoawifoeifheiofoainfoifnaoei");
        registrationService.register(request);
    }

    // @GetMapping(path = "confirm")
    // public String confirm(@RequestParam("token") String token) {
    //     return registrationService.confirmToken(token);
    // } not needed code

}
