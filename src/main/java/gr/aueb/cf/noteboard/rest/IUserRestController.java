package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.*;
import gr.aueb.cf.noteboard.dto.UserInsertDTO;
import gr.aueb.cf.noteboard.dto.UserReadOnlyDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

public interface IUserRestController {

    //get users - filter by username
    @GetMapping("/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsersFiltered(
            @RequestParam(value = "username", required = false) String username);

    //get users by group - filter by username
    @GetMapping("/groups/{groupId}/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsersByGroupFiltered(
            @PathVariable("groupId") Long groupId,
            @RequestParam(value = "username", required = false) String username,
            Principal principal)
            throws AppObjectNotAuthorizedException, AppObjectNotFoundException;

    //get user
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserReadOnlyDTO> getUser(@PathVariable("userId") Long userId)
            throws AppObjectNotFoundException;

    //get user by username
    @GetMapping("/users/username/{username}")
    public ResponseEntity<UserReadOnlyDTO> getUserByUsername(@PathVariable("username") String username)
            throws AppObjectNotFoundException;

    //post user
    @PostMapping("/users/save")
    public ResponseEntity<UserReadOnlyDTO> saveUser(
            @Valid @RequestBody UserInsertDTO userInsertDTO,
            BindingResult bindingResult)
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, ValidationException;
}
