package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
import gr.aueb.cf.noteboard.dto.GroupInsertDTO;
import gr.aueb.cf.noteboard.dto.GroupReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.GroupUpdateDTO;
import gr.aueb.cf.noteboard.service.IGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GroupRestController {

    private final IGroupService groupService;

    //get groups by user
    @GetMapping("/groups")
    public ResponseEntity<Map<String, Object>> getGroups(@RequestParam("userId") Long userId) {

        Map<String, Object> groups = new HashMap<>();

        List<GroupReadOnlyDTO> ownedGroups = groupService.getGroupsByOwnerId(userId);
        groups.put("ownedGroups", ownedGroups);

        List<GroupReadOnlyDTO> joinedGroups = groupService.getGroupsByMemberId(userId);
        groups.put("joinedGroups", joinedGroups);

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    //get groups by owner
    @GetMapping("/ownedGroups")
    public ResponseEntity<List<GroupReadOnlyDTO>> getOwnedGroups(@RequestParam("ownerId") Long ownerId) {

        List<GroupReadOnlyDTO> ownedGroups = groupService.getGroupsByOwnerId(ownerId);
        return new ResponseEntity<>(ownedGroups, HttpStatus.OK);
    }

    //get groups by member
    @GetMapping("/joinedGroups")
    public ResponseEntity<List<GroupReadOnlyDTO>> getJoinedGroups(@RequestParam("memberId") Long memberId) {

            List<GroupReadOnlyDTO> joinedGroups = groupService.getGroupsByMemberId(memberId);
            return new ResponseEntity<>(joinedGroups, HttpStatus.OK);
    }

    //get group
    @GetMapping("/groups/{groupId}")
    public ResponseEntity<GroupReadOnlyDTO> getGroup(@PathVariable("groupId") Long groupId)
            throws AppObjectNotFoundException {

        GroupReadOnlyDTO group = groupService.getGroupById(groupId);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    //todo DOUBLR-CHECK post group
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

    //todo DOUBLE-CHECK put group
    @PutMapping("/groups/{groupId}")
    public ResponseEntity<GroupReadOnlyDTO> updateGroup(
            @PathVariable("groupId") Long groupId,
            @Valid @RequestBody GroupUpdateDTO groupUpdateDTO,
            BindingResult bindingResult)
            throws AppObjectNotFoundException, AppObjectInvalidArgumentException, AppObjectAlreadyExists, ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        GroupReadOnlyDTO group = groupService.updateGroup(groupUpdateDTO);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    //delete group
    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<GroupReadOnlyDTO> deleteGroup(@PathVariable("groupId") Long groupId)
            throws AppObjectNotFoundException {

        GroupReadOnlyDTO group = groupService.getGroupById(groupId);
        groupService.deleteGroup(groupId);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }
}
