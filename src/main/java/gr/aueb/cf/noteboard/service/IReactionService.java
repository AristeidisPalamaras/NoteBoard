package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.ReactionInsertDTO;
import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;

import java.util.List;

public interface IReactionService {

    ReactionReadOnlyDTO insertReaction(ReactionInsertDTO reactionInsertDTO) throws AppObjectAlreadyExists, AppObjectNotFoundException;
    List<ReactionReadOnlyDTO> getReactionsByMessageId(Long messageId);
    List<ReactionReadOnlyDTO> getReactionsByMessageIdAndUserId(Long messageId, Long userId);
}
