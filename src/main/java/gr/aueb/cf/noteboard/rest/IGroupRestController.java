package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.*;
import gr.aueb.cf.noteboard.dto.GroupInsertDTO;
import gr.aueb.cf.noteboard.dto.GroupReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.GroupUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface IGroupRestController {

    @Operation(summary = "Get groups by user ID", description = "Fetch the owned and joined groups of a user by their unique ID")
    ResponseEntity<Map<String, Object>> getGroupsByUser(
            @Parameter(description = "ID of the user") @PathVariable("userId") Long userId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    //get groups by owner
    @GetMapping("/users/{userId}/ownedGroups")
    public ResponseEntity<List<GroupReadOnlyDTO>> getOwnedGroupsByUser(
            @PathVariable("userId") Long userId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    //get groups by member
    @GetMapping("/users/{userId}/joinedGroups")
    public ResponseEntity<List<GroupReadOnlyDTO>> getJoinedGroupsByUser(
            @PathVariable("userId") Long userId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    //get group
    @GetMapping("/groups/{groupId}")
    public ResponseEntity<GroupReadOnlyDTO> getGroup(
            @PathVariable("groupId") Long groupId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    //post group
    @PostMapping("/groups/save")
    public ResponseEntity<GroupReadOnlyDTO> saveGroup(
            @Valid @RequestBody GroupInsertDTO groupInsertDTO,
            BindingResult bindingResult,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectInvalidArgumentException, AppObjectAlreadyExists, ValidationException, AppObjectNotAuthorizedException;

    //put group
    @PutMapping("/groups/{groupId}")
    public ResponseEntity<GroupReadOnlyDTO> updateGroup(
            @PathVariable("groupId") Long groupId,
            @Valid @RequestBody GroupUpdateDTO groupUpdateDTO,
            BindingResult bindingResult,
            Principal principal)
            throws AppObjectInvalidArgumentException, AppObjectNotFoundException, AppObjectNotAuthorizedException, ValidationException;

    //delete group
    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<GroupReadOnlyDTO> deleteGroup(
            @PathVariable("groupId") Long groupId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;
    }
