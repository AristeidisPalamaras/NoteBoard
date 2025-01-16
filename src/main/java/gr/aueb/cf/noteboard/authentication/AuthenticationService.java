package gr.aueb.cf.noteboard.authentication;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.noteboard.dto.AuthenticationRequestDTO;
import gr.aueb.cf.noteboard.dto.AuthenticationResponseDTO;
import gr.aueb.cf.noteboard.model.User;
import gr.aueb.cf.noteboard.repository.UserRepository;
import gr.aueb.cf.noteboard.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto)
            throws AppObjectNotAuthorizedException {
        // Create an authentication token from username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        User user = userRepository.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new AppObjectNotAuthorizedException("User", "User not authorized"));

        // If authentication was successful, generate JWT token
        String token = jwtService.generateToken(user.getUsername(), user.getId(), "ROLE_USER");
        return new AuthenticationResponseDTO(user.getUsername(), user.getId(), token);
    }
}
