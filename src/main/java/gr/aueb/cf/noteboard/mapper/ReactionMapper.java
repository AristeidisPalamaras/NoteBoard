package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.ReactionReadOnlyDTO;
import gr.aueb.cf.noteboard.model.Reaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReactionMapper {

    public ReactionReadOnlyDTO mapToReactionReadOnlyDTO(Reaction reaction) {

        ReactionReadOnlyDTO dto = new ReactionReadOnlyDTO();

        dto.setId(reaction.getId());
        dto.setDescription(reaction.getDescription());
        dto.setUser(reaction.getUser().getUsername());

        return dto;
    }

//    public Reaction mapToReaction(ReactionInsertDTO dto) {
//
//        return null;
//    }
}
