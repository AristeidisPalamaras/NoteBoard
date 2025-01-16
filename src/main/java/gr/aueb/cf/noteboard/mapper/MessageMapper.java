package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.MessageReadOnlyDTO;
import gr.aueb.cf.noteboard.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final UserMapper userMapper;

    public MessageReadOnlyDTO mapToMessageReadOnlyDTO(Message message) {

        MessageReadOnlyDTO dto = new MessageReadOnlyDTO();

        dto.setId(message.getId());
        dto.setText(message.getText());
        dto.setAuthor(userMapper.mapToUserReadOnlyDTO(message.getAuthor()));
        dto.setCreatedAt(message.getCreatedAt());

        return dto;
    }

//    public Message mapToMessage(MessageInsertDTO dto) {
//
//        return null;
//    }
}
