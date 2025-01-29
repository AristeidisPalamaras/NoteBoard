package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
import gr.aueb.cf.noteboard.dto.AuthenticationRequestDTO;
import gr.aueb.cf.noteboard.dto.AuthenticationResponseDTO;
import gr.aueb.cf.noteboard.dto.ResponseMessageDTO;
import gr.aueb.cf.noteboard.dto.ValidationMessageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IAuthRestController {

    @Operation(summary = "Log in user", description = "Allows an existing user to log in the system.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User successfully logged in.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationResponseDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Form validation error.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidationMessageDTO.class, example =
                                    "{\"code\": \"ValidationErrors\", \"description\": {\"username\": \"Username is required\", \"password\": \"Password is required\"}}"))),
            @ApiResponse(
                    responseCode = "406",
                    description = "Log in failed.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"LoginFailure\", \"description\": \"Username or password is incorrect\"}"))),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotFound\", \"description\": \"User with id 0 not found\"}"))),
    })
    ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody(description = "The login details of the user.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationRequestDTO.class)
                    ))
            AuthenticationRequestDTO authenticationRequestDTO,
            BindingResult bindingResult)
            throws AppObjectNotFoundException, ValidationException;
}
