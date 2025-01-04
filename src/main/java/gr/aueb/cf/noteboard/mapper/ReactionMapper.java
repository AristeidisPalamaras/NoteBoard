package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.ReactionInsertDTO;
import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;
import gr.aueb.cf.noteboard.model.Reaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReactionMapper {

    private final MessageMapper messageMapper;
    private final UserMapper userMapper;

    public ReactionReadOnlyDTO mapToReactionReadOnlyDTO(Reaction reaction) {

        ReactionReadOnlyDTO dto = new ReactionReadOnlyDTO();

        dto.setId(reaction.getId());
        dto.setMessage(messageMapper.mapToMessageReadOnlyDTO(reaction.getMessage()));
        dto.setUser(userMapper.mapToUserReadOnlyDTO(reaction.getUser()));

        return new ReactionReadOnlyDTO();
    }

    public Reaction mapToReaction(ReactionInsertDTO dto) {

        Reaction reaction = new Reaction();

        reaction.setMessage(messageMapper.mapToMessage(dto.getMessage()));
        reaction.setUser(userMapper.mapToUser(dto.getUser()));
        reaction.setDescription(dto.getDescription());

        return reaction;
    }
}
