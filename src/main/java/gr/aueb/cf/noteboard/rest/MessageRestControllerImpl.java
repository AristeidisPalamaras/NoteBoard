package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.authentication.AuthorizationService;
import gr.aueb.cf.noteboard.core.exceptions.*;
import gr.aueb.cf.noteboard.dto.MessageInsertDTO;
import gr.aueb.cf.noteboard.dto.MessageReadOnlyDTO;
import gr.aueb.cf.noteboard.service.IMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageRestControllerImpl implements IMessageRestController {

    private final IMessageService messageService;
    private final AuthorizationService authorizationService;

    //get messages by group
    @GetMapping("/groups/{groupId}/messages")
    public ResponseEntity<Page<MessageReadOnlyDTO>> getMessagesByGroup(
            @PathVariable("groupId") Long groupId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortDirection", required = false) String sortDirection,
            Principal principal)
        throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        //you shouldn't be able to see group messages if you are not the owner or a member of the group
        authorizationService.isOwnerOrMemberOrThrow(groupId, principal);

        Page<MessageReadOnlyDTO> messages = messageService.getMessagesByGroupId(
                page, groupId, sortDirection);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    //get messages by group and user
    @GetMapping("/groups/{groupId}/users/{userId}/messages")
    public ResponseEntity<Page<MessageReadOnlyDTO>> getMessagesByGroupAndUser(
            @PathVariable("groupId") Long groupId,
            @PathVariable("userId") Long userId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortDirection", required = false) String sortDirection,
            Principal principal)
        throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        //you shouldn't be able to see group messages if you are not the owner or a member of the group
        authorizationService.isOwnerOrMemberOrThrow(groupId, principal);

        Page<MessageReadOnlyDTO> messages = messageService.getMessagesByGroupIdAndAuthorId(
                page, groupId, userId, sortDirection);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    //The above implementation assumes that the author has already been retrieved by UserRestController.getUsersFiltered(),
    //so the messages can be retrieved by the messageId AND the userId.
    //The alternative implementation bellow gets messages by groupId AND filtering by username.

    //get messages by group - filter by username
//    @GetMapping("/groups/{groupId}/messages")
//    public ResponseEntity<Page<MessageReadOnlyDTO>> getMessagesByGroupFiltered(
//            @PathVariable("groupId") Long groupId,
//            @RequestParam(required = false) String author,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "sortDirection", required = false) String sortDirection,
//            Principal principal)
//            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {
//
//    //you shouldn't be able to see group messages if you are not the owner or a member of the group
//        authorizationService.isOwnerOrMemberOrThrow(groupId, principal);
//
//        Page<MessageReadOnlyDTO> messages = messageService.getMessagesByGroupIdAndAuthorUsernameLike(
//                page, groupId, author, sortDirection);
//
//        return new ResponseEntity<>(messages, HttpStatus.OK);
//    }

    //get message
    @GetMapping("/groups/{groupId}/messages/{messageId}")
    public ResponseEntity<MessageReadOnlyDTO> getMessage(
            @PathVariable("groupId") Long groupId,
            @PathVariable("messageId") Long messageId,
            Principal principal)
        throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        //You shouldn't be able to see a message if you are not the owner or a member of the group
        authorizationService.isOwnerOrMemberOrThrow(groupId, principal);

        MessageReadOnlyDTO message = messageService.getMessageById(messageId);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //post message
    @PostMapping("/messages/save")
    public ResponseEntity<MessageReadOnlyDTO> saveMessage(
            @Valid @RequestBody MessageInsertDTO messageInsertDTO,
            BindingResult bindingResult,
            Principal principal)
        throws AppObjectNotFoundException, ValidationException, AppObjectNotAuthorizedException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        //Logged-in user should not be able to add messages to groups they are not members/owner
        authorizationService.isOwnerOrMemberOrThrow(messageInsertDTO.getGroupId(), principal);

        //Logged-in user should not be able to create a message with another user as author
        authorizationService.isPrincipalOrThrow(messageInsertDTO.getAuthor(), principal);

        MessageReadOnlyDTO message = messageService.insertMessage(messageInsertDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //delete message
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<MessageReadOnlyDTO> deleteMessage(
            @PathVariable("messageId") Long messageId,
            Principal principal)
        throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        //You shouldn't be able to delete a message if you are not the author
        authorizationService.isAuthorOrThrow(messageId, principal);

        MessageReadOnlyDTO message = messageService.getMessageById(messageId);
        messageService.deleteMessage(messageId);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
