package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.*;
import gr.aueb.cf.noteboard.dto.GroupInsertDTO;
import gr.aueb.cf.noteboard.dto.GroupReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.GroupUpdateDTO;
import gr.aueb.cf.noteboard.service.IGroupService;
import gr.aueb.cf.noteboard.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GroupRestController {

    private final IGroupService groupService;
    private final IUserService userService;

    //get groups (owned and joined) by user
    @GetMapping("users/{userId}/groups")
    public ResponseEntity<Map<String, Object>> getGroupsByUser(@PathVariable("userId") Long userId) {

        Map<String, Object> groups = new HashMap<>();

        List<GroupReadOnlyDTO> ownedGroups = groupService.getGroupsByOwnerId(userId);
        groups.put("ownedGroups", ownedGroups);

        List<GroupReadOnlyDTO> joinedGroups = groupService.getGroupsByMemberId(userId);
        groups.put("joinedGroups", joinedGroups);

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // Alternative implementation where owned groups and joined groups are retrieved from different endpoints:

    //get groups by owner
    @GetMapping("/users/{userId}/ownedGroups")
    public ResponseEntity<List<GroupReadOnlyDTO>> getOwnedGroupsByUser(@PathVariable("userId") Long userId) {

        List<GroupReadOnlyDTO> ownedGroups = groupService.getGroupsByOwnerId(userId);
        return new ResponseEntity<>(ownedGroups, HttpStatus.OK);
    }

    //get groups by member
    @GetMapping("/users/{userId}/joinedGroups")
    public ResponseEntity<List<GroupReadOnlyDTO>> getJoinedGroupsByUser(@PathVariable("userId") Long userId) {

            List<GroupReadOnlyDTO> joinedGroups = groupService.getGroupsByMemberId(userId);
            return new ResponseEntity<>(joinedGroups, HttpStatus.OK);
    }

    //get group
    @GetMapping("/groups/{groupId}")
    public ResponseEntity<GroupReadOnlyDTO> getGroup(
            @PathVariable("groupId") Long groupId,
            Principal principal)
        throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        // You shouldn't be able to see the information of a group if you are not the owner or a member of the group
        Long principalId = userService.getUserByUsername(principal.getName()).getId();
        if (!groupService.isOwner(groupId, principalId) && !groupService.isMember(groupId, principalId)) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + principalId + " not authorized");
        }

        GroupReadOnlyDTO group = groupService.getGroupById(groupId);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    //post group
    @PostMapping("/groups/save")
    public ResponseEntity<GroupReadOnlyDTO> saveGroup(
            @Valid @RequestBody GroupInsertDTO groupInsertDTO,
            BindingResult bindingResult)
        throws AppObjectNotFoundException, AppObjectInvalidArgumentException, AppObjectAlreadyExists, ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        GroupReadOnlyDTO group = groupService.insertGroup(groupInsertDTO);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    //put group
    @PutMapping("/groups/{groupId}")
    public ResponseEntity<GroupReadOnlyDTO> updateGroup(
            @PathVariable("groupId") Long groupId,
            @Valid @RequestBody GroupUpdateDTO groupUpdateDTO,
            BindingResult bindingResult,
            Principal principal)
        throws AppObjectInvalidArgumentException, AppObjectAlreadyExists, AppObjectNotFoundException,
            AppObjectNotAuthorizedException, ValidationException {

        //You shouldn't be able to update a group if you are not the owner
        Long principalId = userService.getUserByUsername(principal.getName()).getId();
        if (!groupService.isOwner(groupId, principalId)) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + principalId + " not authorized");
        }

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        GroupReadOnlyDTO group = groupService.updateGroup(groupUpdateDTO);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    //delete group
    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<GroupReadOnlyDTO> deleteGroup(
            @PathVariable("groupId") Long groupId,
            Principal principal)
        throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        //You shouldn't able to delete a group if you are not the owner
        Long peincipalId = userService.getUserByUsername(principal.getName()).getId();
        if (!groupService.isOwner(groupId, peincipalId)) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + peincipalId + " not authorized");
        }

        GroupReadOnlyDTO group = groupService.getGroupById(groupId);
        groupService.deleteGroup(groupId);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }
}
