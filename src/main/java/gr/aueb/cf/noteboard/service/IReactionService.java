package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.ReactionInsertDTO;
import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;

import java.util.List;

public interface IReactionService {

    ReactionReadOnlyDTO insertReaction(ReactionInsertDTO reactionInsertDTO) throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, AppObjectNotFoundException;
    List<ReactionReadOnlyDTO> getReactionsByMessageId(Long messageId);
}
