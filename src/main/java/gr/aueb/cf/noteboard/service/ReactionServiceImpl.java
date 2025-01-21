package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.ReactionInsertDTO;
import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;
import gr.aueb.cf.noteboard.mapper.ReactionMapper;
import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.Reaction;
import gr.aueb.cf.noteboard.model.User;
import gr.aueb.cf.noteboard.repository.MessageRepository;
import gr.aueb.cf.noteboard.repository.ReactionRepository;
import gr.aueb.cf.noteboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements IReactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactionServiceImpl.class);
    private final ReactionRepository reactionRepository;
    private final ReactionMapper reactionMapper;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;


    @Transactional
    public ReactionReadOnlyDTO insertReaction(ReactionInsertDTO reactionInsertDTO)
            throws AppObjectAlreadyExists, AppObjectNotFoundException {

        if (reactionRepository.findReactionByMessageIdAndUserIdAndDescription(
                reactionInsertDTO.getMessageId(),
                reactionInsertDTO.getUser(),
                reactionInsertDTO.getDescription()).isPresent()) {
            throw new AppObjectAlreadyExists("Reaction", "Reaction with description " + reactionInsertDTO.getDescription()
                    + " already exists for user with name " + reactionInsertDTO.getUser() + " and message with id "
                    + reactionInsertDTO.getMessageId());
        }

        Reaction reaction = new Reaction();
        reaction.setDescription(reactionInsertDTO.getDescription());

        Message message = messageRepository.findById(reactionInsertDTO.getMessageId())
                .orElseThrow(() -> new AppObjectNotFoundException("Message", "Mesage with id " + reactionInsertDTO.getMessageId() + " not found"));

        reaction.setMessage(message);
        // message.addReaction(reaction);

        User user = userRepository.findUserByUsername(reactionInsertDTO.getUser())
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with name " + reactionInsertDTO.getUser() + " not found"));

        reaction.setUser(user);
        // user.addReaction(reaction);

        reaction = reactionRepository.save(reaction);

        return reactionMapper.mapToReactionReadOnlyDTO(reaction);
    }

    @Transactional
    public List<ReactionReadOnlyDTO> getReactionsByMessageId(Long messageId) {

        List<ReactionReadOnlyDTO> reactions;

        reactions = reactionRepository.findReactionsByMessageId(messageId)
                .stream()
                .map(reactionMapper::mapToReactionReadOnlyDTO)
                .collect(Collectors.toList());

        return reactions;
    }

    @Transactional
    public List<ReactionReadOnlyDTO> getReactionsByMessageIdAndUserId(Long messageId, Long userId) {

        List<ReactionReadOnlyDTO> reactions;

        reactions = reactionRepository.findReactionsByMessageIdAndUserId(messageId, userId)
                .stream()
                .map(reactionMapper::mapToReactionReadOnlyDTO)
                .collect(Collectors.toList());

        return reactions;
    }
}
