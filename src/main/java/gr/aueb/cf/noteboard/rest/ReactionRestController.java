package gr.aueb.cf.noteboard.rest;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.core.exceptions.ValidationException;
import gr.aueb.cf.noteboard.dto.ReactionInsertDTO;
import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;
import gr.aueb.cf.noteboard.service.IReactionService;
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
public class ReactionRestController {

    private final IReactionService reactionService;

    //get reactions by message
    @GetMapping("/reactions/per-message/{messageId}")
    public ResponseEntity<List<ReactionReadOnlyDTO>> getReactionsByMessage(@PathVariable("messageId") Long messageId) {

        List<ReactionReadOnlyDTO> reactions = reactionService.getReactionsByMessageId(messageId);

        return new ResponseEntity<>(reactions, HttpStatus.OK);
    }

    //get reactions by message AND user
    @GetMapping("/reactions/per-message{messageId}/per-user/{userId}")
    public ResponseEntity<List<ReactionReadOnlyDTO>> getReactionsByMessageAndUser(
            @PathVariable("messageId") Long messageId,
            @PathVariable("userId") Long userId) {

        List<ReactionReadOnlyDTO> reactions = reactionService.getReactionsByMessageIdAndUserId(messageId, userId);

        return new ResponseEntity<>(reactions, HttpStatus.OK);
    }

    //post reaction
    @PostMapping("/reactions/save")
    public ResponseEntity<ReactionReadOnlyDTO> saveReaction(
            @Valid @RequestBody ReactionInsertDTO reactionInsertDTO,
            BindingResult bindingResult)
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, AppObjectNotFoundException, ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        ReactionReadOnlyDTO reaction = reactionService.insertReaction(reactionInsertDTO);
        return new ResponseEntity<>(reaction, HttpStatus.CREATED);
    }
}
