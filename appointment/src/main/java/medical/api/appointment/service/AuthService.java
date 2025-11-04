package medical.api.appointment.service;

import medical.api.appointment.auth.JWTService;
import medical.api.appointment.auth.UserAuthenticated;
import medical.api.appointment.dto.SignupRequestDTO;
import medical.api.appointment.model.User;
import medical.api.appointment.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepo;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;


    public AuthService(UserRepository userRepo, JWTService jwtService, @Lazy AuthenticationManager authManager, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(String username, String password) {
        var authToken = new UsernamePasswordAuthenticationToken(username, password);
        var authentication = authManager.authenticate(authToken);
        return jwtService.generateToken(authentication);
    }

    public String registerUser(SignupRequestDTO data) {
        if (userRepo.findByUsername(data.username()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User();
        user.setUsername(data.username());
        user.setPassword(passwordEncoder.encode(data.password()));
        userRepo.save(user);

        return jwtService.generateToken(
                new UsernamePasswordAuthenticationToken(user.getUsername(), data.password())
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .map(UserAuthenticated::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
