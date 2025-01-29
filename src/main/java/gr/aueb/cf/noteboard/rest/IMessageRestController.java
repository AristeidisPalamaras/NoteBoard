package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
import gr.aueb.cf.noteboard.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.security.Principal;

public interface IMessageRestController {

    @Operation(summary = "Get messages by group", description = "Fetch the messages posted to a group by the group ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved messages."),
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

    ResponseEntity<Page<MessageReadOnlyDTO>> getMessagesByGroup(
            @Parameter(description = "The ID of the group", required = true) Long groupId,
            @Parameter(description = "The number of the requested page") int page,
            @Parameter(description = "The direction of sorting of the results") String sortDirection,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    @Operation(summary = "Get messages by group and user", description = "Fetch the messages posted to a group by a user by the group ID and user ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved messages."),
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
    ResponseEntity<Page<MessageReadOnlyDTO>> getMessagesByGroupAndUser(
            @Parameter(description = "The ID of the group", required = true) Long groupId,
            @Parameter(description = "The ID of the user", required = true) Long userId,
            @Parameter(description = "The number of the requested page") int page,
            @Parameter(description = "The direction of sorting o the results") String sortDirection,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    @Operation(summary = "Get message", description = "Get a message by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved message.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MessageReadOnlyDTO.class))),
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
    ResponseEntity<MessageReadOnlyDTO> getMessage(
            @Parameter(description = "The ID of the group", required = true) Long groupId,
            @Parameter(description = "The ID of the message", required = true) Long messageId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    @Operation(summary = "Create message", description = "Adds a new message to the system.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully added new message.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MessageReadOnlyDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation errors.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidationMessageDTO.class, example =
                                    "{\"code\": \"ValidationErrors\", \"description\": {\"author\": \"Author is required\", \"text\": \"Message can not be empty\"}}"))),
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
    ResponseEntity<MessageReadOnlyDTO> saveMessage(
            @RequestBody(description = "Details of the message to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MessageInsertDTO.class)
                    ))
            MessageInsertDTO messageInsertDTO,
            BindingResult bindingResult,
            Principal principal)
            throws AppObjectNotFoundException, ValidationException, AppObjectNotAuthorizedException;

    @Operation(summary = "Delete message", description = "Deletes an existing message by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted message.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MessageReadOnlyDTO.class))),
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
    ResponseEntity<MessageReadOnlyDTO> deleteMessage(
            @Parameter(description = "The ID of the message", required = true) Long messageId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;
}
