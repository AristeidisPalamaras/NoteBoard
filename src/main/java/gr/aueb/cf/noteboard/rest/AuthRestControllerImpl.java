package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.authentication.AuthenticationService;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
import gr.aueb.cf.noteboard.dto.AuthenticationRequestDTO;
import gr.aueb.cf.noteboard.dto.AuthenticationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestControllerImpl implements IAuthRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRestControllerImpl.class);

    private final AuthenticationService authenticationService;

    //login
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO,
            BindingResult bindingResult)
            throws AppObjectNotAuthorizedException, ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.authenticate(authenticationRequestDTO);
        return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);
    }
}
