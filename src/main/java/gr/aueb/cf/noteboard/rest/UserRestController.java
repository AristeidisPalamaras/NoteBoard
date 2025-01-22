package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.authentication.AuthorizationService;
import gr.aueb.cf.noteboard.core.exceptions.*;
import gr.aueb.cf.noteboard.dto.UserInsertDTO;
import gr.aueb.cf.noteboard.dto.UserReadOnlyDTO;
import gr.aueb.cf.noteboard.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserService userService;
    private final AuthorizationService authorizationService;

    //get users - filter by username
    @GetMapping("/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsersFiltered(
            @RequestParam(value = "username", required = false) String username) {

        List<UserReadOnlyDTO> users = userService.getUsersByUsernameLike(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //get users by group - filter by username
    @GetMapping("/groups/{groupId}/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsersByGroupFiltered(
            @PathVariable("groupId") Long groupId,
            @RequestParam(value = "username", required = false) String username,
            Principal principal)
        throws AppObjectNotAuthorizedException, AppObjectNotFoundException {

        // You shouldn't be able to see the members of a group if you are not the owner or a member of the group
        authorizationService.isOwnerOrMemberOrThrow(groupId, principal);

        List<UserReadOnlyDTO> users = userService.getUsersByGroupIdAndUsernameLike(groupId, username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //get user
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserReadOnlyDTO> getUser(@PathVariable("userId") Long userId)
        throws AppObjectNotFoundException {

        UserReadOnlyDTO user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //post user
    @PostMapping("/users/save")
    public ResponseEntity<UserReadOnlyDTO> saveUser(
            @Valid @RequestBody UserInsertDTO userInsertDTO,
            BindingResult bindingResult)
        throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        UserReadOnlyDTO user = userService.insertUser(userInsertDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
