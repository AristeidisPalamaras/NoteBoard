package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
import gr.aueb.cf.noteboard.dto.UserInsertDTO;
import gr.aueb.cf.noteboard.dto.UserReadOnlyDTO;
import gr.aueb.cf.noteboard.model.User;
import gr.aueb.cf.noteboard.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserService userService;

    //get all users + search by username
    @GetMapping("/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsersFiltered(
            @RequestParam(value = "username", required = false) String username) {

        List<UserReadOnlyDTO> users = userService.getUsersByUsernameLike(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //get users by group + search by username
    @GetMapping("/groups/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsersByGroupFiltered(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "groupId", required = false) Long groupId) {

        List<UserReadOnlyDTO> users = userService.getUsersByGroupIdAndUsernameLike(groupId, username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //get user
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserReadOnlyDTO> getUser(@PathVariable Long userId)
        throws AppObjectNotFoundException {

        UserReadOnlyDTO user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //todo DOUBLE-CHECK post user
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
