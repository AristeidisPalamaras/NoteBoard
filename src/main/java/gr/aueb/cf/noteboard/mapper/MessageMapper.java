package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.MessageInsertDTO;
import gr.aueb.cf.noteboard.dto.MessageReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.MessageUpdateDTO;
import gr.aueb.cf.noteboard.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final UserMapper userMapper;
    private final GroupMapper groupMapper;
    private final ViewsMapper viewsMapper;

    public MessageReadOnlyDTO mapToMessageReadOnlyDTO(Message message) {

        MessageReadOnlyDTO dto = new MessageReadOnlyDTO();

        dto.setId(message.getId());
        dto.setText(message.getText());
        dto.setIsCompleted(message.getIsCompleted());
        dto.setAuthor(userMapper.mapToUserReadOnlyDTO(message.getAuthor()));
        dto.setGroup(groupMapper.mapToGroupReadOnlyDTO(message.getGroup()));
        message.getViews().forEach(view -> {
            dto.getViews().add(viewsMapper.mapToViewsReadOnlyDTO(view));
        });

        return dto;
    }

    public Message mapToMessage(MessageInsertDTO dto) {

        Message message = new Message();

        message.setAuthor(userMapper.mapToUser(dto.getAuthor()));
        message.setGroup(groupMapper.mapToGroup(dto.getGroup()));
        message.setText(dto.getText());

        return message;
    }

    public Message mapToMessage(MessageUpdateDTO dto) {

        Message message = new Message();

        message.setId(dto.getId());
        message.setIsCompleted(dto.getIsCompleted());

        return new Message();
    }
}
