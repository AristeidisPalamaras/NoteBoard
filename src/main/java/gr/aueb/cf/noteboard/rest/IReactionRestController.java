package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
import gr.aueb.cf.noteboard.dto.ReactionInsertDTO;
import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

public interface IReactionRestController {

    //get reactions by message
    @GetMapping("/groups/{groupId}/messages/{messageId}/reactions")
    public ResponseEntity<List<ReactionReadOnlyDTO>> getReactionsByMessage(
            @PathVariable("groupId") Long groupId,
            @PathVariable("messageId") Long messageId,
            Principal principal)
            throws AppObjectNotAuthorizedException, AppObjectNotFoundException;

    //get reactions by message and user
    @GetMapping("/groups/{groupId}/messages/{messageId}/users/{userId}/reactions")
    public ResponseEntity<List<ReactionReadOnlyDTO>> getReactionsByMessageAndUser(
            @PathVariable("groupId") Long groupId,
            @PathVariable("messageId") Long messageId,
            @PathVariable("userId") Long userId,
            Principal principal)
            throws AppObjectNotAuthorizedException, AppObjectNotFoundException;

    //post reaction
    @PostMapping("/reactions/save")
    public ResponseEntity<ReactionReadOnlyDTO> saveReaction(
            @Valid @RequestBody ReactionInsertDTO reactionInsertDTO,
            BindingResult bindingResult,
            Principal principal)
            throws AppObjectAlreadyExists, AppObjectNotFoundException, ValidationException, AppObjectNotAuthorizedException;
}
