package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.MessageInsertDTO;
import gr.aueb.cf.noteboard.dto.MessageReadOnlyDTO;
import gr.aueb.cf.noteboard.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final UserMapper userMapper;
    private final GroupMapper groupMapper;
    private final ReactionMapper reactionMapper;

    public MessageReadOnlyDTO mapToMessageReadOnlyDTO(Message message) {

        MessageReadOnlyDTO dto = new MessageReadOnlyDTO();

        dto.setId(message.getId());
        dto.setText(message.getText());
        dto.setAuthor(message.getAuthor().getUsername());
        dto.setCreatedAt(message.getCreatedAt());

        return dto;
    }

//    public Message mapToMessage(MessageInsertDTO dto) {
//
//        return null;
//    }
}
