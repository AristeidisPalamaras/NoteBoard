package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.*;
import gr.aueb.cf.noteboard.dto.ReactionInsertDTO;
import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;
import gr.aueb.cf.noteboard.service.IGroupService;
import gr.aueb.cf.noteboard.service.IReactionService;
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
public class ReactionRestController {

    private final IReactionService reactionService;
    private final IUserService userService;
    private final IGroupService groupService;

    //get reactions by message
    @GetMapping("/groups/{groupId}/messages/{messageId}/reactions")
    public ResponseEntity<List<ReactionReadOnlyDTO>> getReactionsByMessage(
            @PathVariable("groupId") Long groupId,
            @PathVariable("messageId") Long messageId,
            Principal principal)
        throws AppObjectNotAuthorizedException, AppObjectNotFoundException {

        //You shouldn't be able to see reactions to a group message if you are not the owner or a member of the group
        Long principalId = userService.getUserByUsername(principal.getName()).getId();
        if (!groupService.isOwner(groupId, principalId) && !groupService.isMember(groupId, principalId)) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + principalId + " not authorized");
        }

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
        Long principalId = userService.getUserByUsername(principal.getName()).getId();
        if (!groupService.isOwner(groupId, principalId) && !groupService.isMember(groupId, principalId)) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + principalId + " not authorized");
        }

        List<ReactionReadOnlyDTO> reactions = reactionService.getReactionsByMessageIdAndUserId(messageId, userId);

        return new ResponseEntity<>(reactions, HttpStatus.OK);
    }

    //post reaction
    @PostMapping("/reactions/save")
    public ResponseEntity<ReactionReadOnlyDTO> saveReaction(
            @Valid @RequestBody ReactionInsertDTO reactionInsertDTO,
            BindingResult bindingResult)
        throws AppObjectAlreadyExists, AppObjectNotFoundException, ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        ReactionReadOnlyDTO reaction = reactionService.insertReaction(reactionInsertDTO);
        return new ResponseEntity<>(reaction, HttpStatus.CREATED);
    }
}
