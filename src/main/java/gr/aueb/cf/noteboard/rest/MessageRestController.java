package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageRestController {

    private final IMessageService messageService;

    //get messages
    @GetMapping("/messages")
    public ResponseEntity<Page<MessageReadOnlyDTO>> getMessages(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam("groupId") Long groupId,
            @RequestParam(value = "authorId", required = false) Long authorId,
            @RequestParam(value = "sortDirection", required = false) String sortDirection)
            throws AppObjectNotFoundException {

        Page<MessageReadOnlyDTO> messages = messageService.getMessagesByGroupIdAndAuthorId(
                page, groupId, authorId, sortDirection);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    //The above implementation assumes that the author has already been retrieved by a getUserByUsernameLike(),
    //so the messages can be retrieved by messageId AND userId
    //The alternative implementation below gets messages by groupId AND username like
//    @GetMapping("/messages")
//    public ResponseEntity<Page<MessageReadOnlyDTO>> getMessages(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam("groupId") Long groupId,
//            @RequestParam(required = false) String author,
//            @RequestParam(value = "sortDirection", required = false) String sortDirection)
//            throws AppObjectNotFoundException {
//
//        Page<MessageReadOnlyDTO> messages = messageService.getMessagesByGroupIdAndAuthorUsernameLike(
//                page, groupId, author, sortDirection);
//
//        return new ResponseEntity<>(messages, HttpStatus.OK);
//    }

    //get message

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<MessageReadOnlyDTO> getMessage(@PathVariable("messageId") Long messageId)
        throws AppObjectNotFoundException {

        MessageReadOnlyDTO message = messageService.getMessageById(messageId);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //todo DOUBLE-CHECK post message
    @PostMapping("/messages/save")
    public ResponseEntity<MessageReadOnlyDTO> saveMessage(
            @Valid @RequestBody MessageInsertDTO messageInsertDTO,
            BindingResult bindingResult)
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, AppObjectNotFoundException, ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        MessageReadOnlyDTO message = messageService.insertMessage(messageInsertDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //delete message
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<MessageReadOnlyDTO> deleteMessage(@PathVariable("messageId") Long messageId)
        throws AppObjectNotFoundException {

        MessageReadOnlyDTO message = messageService.getMessageById(messageId);
        messageService.deleteMessage(messageId);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
