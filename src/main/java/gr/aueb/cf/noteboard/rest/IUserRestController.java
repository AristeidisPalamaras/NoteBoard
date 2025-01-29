package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.*;
import gr.aueb.cf.noteboard.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.List;

public interface IUserRestController {

    @Operation(summary = "Search users", description = "Fetch users filtered by their username.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved users.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserReadOnlyDTO.class)))),
            @ApiResponse(
                    responseCode = "401",
                    description = "User not authenticated.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotAuthenticated\", \"description\": \"User needs to authenticate in order to access this resource\"}"))),
    })
    ResponseEntity<List<UserReadOnlyDTO>> getUsersFiltered(
            @Parameter(description = "Query string for username search") String username);

    @Operation(summary = "Search users within group", description = "Fetch the users that belong to a group (owner/members) by the group ID, filtered by their username.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved users.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserReadOnlyDTO.class)))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Group not found. User not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotFound\", \"description\": \"User with id 0 not found\"}"))),
            @ApiResponse(
                    responseCode = "403",
                    description = "User not authorized.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotAuthorized\", \"description\": \"User with id 0 not authorized\"}"))),
            @ApiResponse(
                    responseCode = "401",
                    description = "User not authenticated.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotAuthenticated\", \"description\": \"User needs to authenticate in order to access this resource\"}"))),
    })
    ResponseEntity<List<UserReadOnlyDTO>> getUsersByGroupFiltered(
            @Parameter(description = "The ID of the group", required = true) Long groupId,
            @Parameter(description = "Query string for username search") String username,
            Principal principal)
            throws AppObjectNotAuthorizedException, AppObjectNotFoundException;

    @Operation(summary = "Get user by ID", description = "Fetch a user by their ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved user.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserReadOnlyDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotFound\", \"description\": \"User with id 0 not found\"}"))),
            @ApiResponse(
                    responseCode = "401",
                    description = "User not authenticated.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotAuthenticated\", \"description\": \"User needs to authenticate in order to access this resource\"}"))),
    })
    ResponseEntity<UserReadOnlyDTO> getUser(
            @Parameter(description = "The ID of the user", required = true) Long userId)
            throws AppObjectNotFoundException;

    @Operation(summary = "Get user by username", description = "Fetch a user by their username.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved user.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserReadOnlyDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotFound\", \"description\": \"User with name user1@foo not found\"}"))),
            @ApiResponse(
                    responseCode = "401",
                    description = "User not authenticated.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotAuthenticated\", \"description\": \"User needs to authenticate in order to access this resource\"}"))),
    })
    ResponseEntity<UserReadOnlyDTO> getUserByUsername(
            @Parameter(description = "The username of the user", required = true) String username)
            throws AppObjectNotFoundException;

    @Operation(summary = "Create user", description = "Add a new user to the system.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully added new user.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserReadOnlyDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation errors.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidationMessageDTO.class, example =
                                    "{\"code\": \"ValidationErrors\", \"description\": {\"username\": \"Name is required\", \"password\": \"Invalid password\"}}"))),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserAlreadyExists\", \"description\": \"User with name user1@foo already exists\"}"))),
            @ApiResponse(
                    responseCode = "406",
                    description = "Password and confirmPassword do not match.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"PasswordInvalidArgument\", \"description\": \"Password and confirmPassword must match\"}"))),
    })
    ResponseEntity<UserReadOnlyDTO> saveUser(
            @RequestBody(description = "Details of the user to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserInsertDTO.class)
                    ))
            UserInsertDTO userInsertDTO,
            BindingResult bindingResult)
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, ValidationException;
}
