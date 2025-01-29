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

public interface IGroupRestController {

    @Operation(summary = "Get groups by user ID", description = "Fetch the owned and joined groups of a user by their ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved groups.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GroupsByUserReadOnlyDTO.class))),
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
    ResponseEntity<GroupsByUserReadOnlyDTO> getGroupsByUser(
            @Parameter(description = "The ID of the user", required = true) Long userId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    @Operation(summary = "Get groups by owner ID", description = "Fetch the owned groups of a user by their ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved groups.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GroupReadOnlyDTO.class)))),
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
    ResponseEntity<List<GroupReadOnlyDTO>> getOwnedGroupsByUser(
            @Parameter(description = "The ID of the owner", required = true) Long userId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    @Operation(summary = "Get groups by member ID", description = "Fetch the joined groups of a user by their ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved groups.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GroupReadOnlyDTO.class)))),
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
    ResponseEntity<List<GroupReadOnlyDTO>> getJoinedGroupsByUser(
            @Parameter(description = "The ID of the member", required = true) Long userId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    @Operation(summary = "Get group by its ID", description = "Fetch a group by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved group.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GroupReadOnlyDTO.class))),
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
    ResponseEntity<GroupReadOnlyDTO> getGroup(
            @Parameter(description = "The ID of the group", required = true) Long groupId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    @Operation(summary = "Create group", description = "Add a new group to the system")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully added new group.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GroupReadOnlyDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation errors.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidationMessageDTO.class, example =
                                    "{\"code\": \"ValidationErrors\", \"description\": {\"name\": \"Name is required\", \"owner\": \"Owner is required\"}}"))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Group already exists for current user.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"GroupAlreadyExists\", \"description\": \"Group with name FOO already exists for user with name user1@foo\"}"))),
            @ApiResponse(
                    responseCode = "406",
                    description = "User can not be added to the group.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserInvalidArgument\", \"description\": \"User with name user1@foo is the owner of the group and can not be added as member\"}"))),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
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
    ResponseEntity<GroupReadOnlyDTO> saveGroup(
            @RequestBody(description = "Details of the group to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GroupInsertDTO.class)
                    ))
            GroupInsertDTO groupInsertDTO,
            BindingResult bindingResult,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectInvalidArgumentException, AppObjectAlreadyExists, ValidationException, AppObjectNotAuthorizedException;

    @Operation(summary = "Update group", description = "Update an existing group by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated group.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GroupReadOnlyDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation errors.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidationMessageDTO.class, example =
                                    "{\"code\": \"ValidationErrors\", \"description\": {\"id\": \"Id is required\"}}"))),
            @ApiResponse(
                    responseCode = "406",
                    description = "User can not be added to the group.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessageDTO.class, example =
                                    "{\"code\": \"UserInvalidArgument\", \"description\": \"User with name user1@foo is already a member of the group\"}"))),
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
    ResponseEntity<GroupReadOnlyDTO> updateGroup(
            @Parameter(description = "The ID of the group", required = true) Long groupId,
            @RequestBody(description = "Details of the group to be updated",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GroupUpdateDTO.class)
                    ))
            GroupUpdateDTO groupUpdateDTO,
            BindingResult bindingResult,
            Principal principal)
            throws AppObjectInvalidArgumentException, AppObjectNotFoundException, AppObjectNotAuthorizedException, ValidationException;

    @Operation(summary = "Delete group", description = "Delete an existing group by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted group.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GroupReadOnlyDTO.class))),
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
    ResponseEntity<GroupReadOnlyDTO> deleteGroup(
            @Parameter(description = "The ID of the group", required = true) Long groupId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;
    }
