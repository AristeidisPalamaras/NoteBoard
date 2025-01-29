package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
import gr.aueb.cf.noteboard.dto.ReactionInsertDTO;
import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.ResponseMessageDTO;
import gr.aueb.cf.noteboard.dto.ValidationMessageDTO;
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

public interface IReactionRestController {

    @Operation(summary = "Get reactions by message", description = "Fetch the reaction to a message by the message ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved reactions.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReactionReadOnlyDTO.class)))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Message not found. User not found (upon checking logged-in user authorization)",
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
    ResponseEntity<List<ReactionReadOnlyDTO>> getReactionsByMessage(
            @Parameter(description = "The ID of the group", required = true) Long groupId,
            @Parameter(description = "The ID of the message", required = true) Long messageId,
            Principal principal)
            throws AppObjectNotAuthorizedException, AppObjectNotFoundException;

    @Operation(summary = "Get reaction by message and user", description = "Fetch the reactions of a user to a message by the message ID and the user ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved reactions.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReactionReadOnlyDTO.class)))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Message not found. User not found.",
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
    ResponseEntity<List<ReactionReadOnlyDTO>> getReactionsByMessageAndUser(
            @Parameter(description = "The ID of the group", required = true) Long groupId,
            @Parameter(description = "The ID of the message", required = true) Long messageId,
            @Parameter(description = "The ID of the user", required = true) Long userId,
            Principal principal)
            throws AppObjectNotAuthorizedException, AppObjectNotFoundException;

    @Operation(summary = "Create reaction", description = "Adds a new reaction to the system.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully created the reaction.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReactionReadOnlyDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation errors.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidationMessageDTO.class, example =
                                    "{\"code\": \"ValidationErrors\", \"description\": {\"messageId\": \"The message is required\", \"user\": \"The user is required\"}}"))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Message not found. User not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserNotFound\", \"description\": \"User with id 0 not found\"}"))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Reaction already exists.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"ReactionAlreadyExists\", \"description\": \"Reaction with description LOL already exists for user with name user1@foo and message with id 1\"}"))),
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
    ResponseEntity<ReactionReadOnlyDTO> saveReaction(
            @RequestBody(description = "Details of the reaction to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReactionInsertDTO.class)
                    ))
            ReactionInsertDTO reactionInsertDTO,
            BindingResult bindingResult,
            Principal principal)
            throws AppObjectAlreadyExists, AppObjectNotFoundException, ValidationException, AppObjectNotAuthorizedException;
}
