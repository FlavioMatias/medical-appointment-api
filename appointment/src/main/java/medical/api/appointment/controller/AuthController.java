package medical.api.appointment.controller;

import medical.api.appointment.dto.LoginRequestDTO;
import medical.api.appointment.dto.SignupRequestDTO;
import medical.api.appointment.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO data) {
        return authService.authenticate(data.username(), data.password());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDTO data) {
        return authService.registerUser(data);
    }
}

