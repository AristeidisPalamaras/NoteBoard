package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
import gr.aueb.cf.noteboard.dto.MessageInsertDTO;
import gr.aueb.cf.noteboard.dto.MessageReadOnlyDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

public interface IMessageRestController {

    //get messages by group
    @GetMapping("/groups/{groupId}/messages")
    public ResponseEntity<Page<MessageReadOnlyDTO>> getMessagesByGroup(
            @PathVariable("groupId") Long groupId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortDirection", required = false) String sortDirection,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    //get messages by group and user
    @GetMapping("/groups/{groupId}/users/{userId}/messages")
    public ResponseEntity<Page<MessageReadOnlyDTO>> getMessagesByGroupAndUser(
            @PathVariable("groupId") Long groupId,
            @PathVariable("userId") Long userId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortDirection", required = false) String sortDirection,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    //get message
    @GetMapping("/groups/{groupId}/messages/{messageId}")
    public ResponseEntity<MessageReadOnlyDTO> getMessage(
            @PathVariable("groupId") Long groupId,
            @PathVariable("messageId") Long messageId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;

    //post message
    @PostMapping("/messages/save")
    public ResponseEntity<MessageReadOnlyDTO> saveMessage(
            @Valid @RequestBody MessageInsertDTO messageInsertDTO,
            BindingResult bindingResult,
            Principal principal)
            throws AppObjectNotFoundException, ValidationException, AppObjectNotAuthorizedException;

    //delete message
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<MessageReadOnlyDTO> deleteMessage(
            @PathVariable("messageId") Long messageId,
            Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException;
}
