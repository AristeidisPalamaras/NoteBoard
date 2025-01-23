package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.authentication.AuthorizationService;
import gr.aueb.cf.noteboard.core.exceptions.*;
import gr.aueb.cf.noteboard.dto.ReactionInsertDTO;
import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;
import gr.aueb.cf.noteboard.repository.MessageRepository;
import gr.aueb.cf.noteboard.service.IReactionService;
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
public class ReactionRestController {

    private final IReactionService reactionService;
    private final AuthorizationService authorizationService;
    private final MessageRepository messageRepository;

    //get reactions by message
    @GetMapping("/groups/{groupId}/messages/{messageId}/reactions")
    public ResponseEntity<List<ReactionReadOnlyDTO>> getReactionsByMessage(
            @PathVariable("groupId") Long groupId,
            @PathVariable("messageId") Long messageId,
            Principal principal)
        throws AppObjectNotAuthorizedException, AppObjectNotFoundException {

        //You shouldn't be able to see reactions to a group message if you are not the owner or a member of the group
        authorizationService.isOwnerOrMemberOrThrow(groupId, principal);

        List<ReactionReadOnlyDTO> reactions = reactionService.getReactionsByMessageId(messageId);

        return new ResponseEntity<>(reactions, HttpStatus.OK);
    }

    //get reactions by message and user
    @GetMapping("/groups/{groupId}/messages/{messageId}/users/{userId}/reactions")
    public ResponseEntity<List<ReactionReadOnlyDTO>> getReactionsByMessageAndUser(
            @PathVariable("groupId") Long groupId,
            @PathVariable("messageId") Long messageId,
            @PathVariable("userId") Long userId,
            Principal principal)
        throws AppObjectNotAuthorizedException, AppObjectNotFoundException {

        //You shouldn't be able to see reactions to a group message if you are not the owner or a member of the group
        authorizationService.isOwnerOrMemberOrThrow(groupId, principal);

        List<ReactionReadOnlyDTO> reactions = reactionService.getReactionsByMessageIdAndUserId(messageId, userId);

        return new ResponseEntity<>(reactions, HttpStatus.OK);
    }

    //post reaction
    @PostMapping("/reactions/save")
    public ResponseEntity<ReactionReadOnlyDTO> saveReaction(
            @Valid @RequestBody ReactionInsertDTO reactionInsertDTO,
            BindingResult bindingResult,
            Principal principal)
        throws AppObjectAlreadyExists, AppObjectNotFoundException, ValidationException, AppObjectNotAuthorizedException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        //logged-in user should not be able to add a reaction with the id of a different user
        authorizationService.isPrincipalOrThrow(reactionInsertDTO.getUser(), principal);

        //User should not be able to add reaction to a message posted to a group that they are not a member/owner
        Long groupId = messageRepository.findMessageById(reactionInsertDTO.getMessageId())
                .orElseThrow(() -> new AppObjectNotFoundException("Message",
                        "Message with id " + reactionInsertDTO.getMessageId() + " not found"))
                .getGroup()
                .getId();
        authorizationService.isOwnerOrMemberOrThrow(groupId, principal);

        ReactionReadOnlyDTO reaction = reactionService.insertReaction(reactionInsertDTO);
        return new ResponseEntity<>(reaction, HttpStatus.CREATED);
    }
}
