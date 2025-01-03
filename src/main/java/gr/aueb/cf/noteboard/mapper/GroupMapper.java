package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.GroupInsertDTO;
import gr.aueb.cf.noteboard.dto.GroupReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.GroupUpdateDTO;
import gr.aueb.cf.noteboard.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupMapper {

    private final UserMapper userMapper;
    private final MessageMapper messageMapper;

    public GroupReadOnlyDTO mapToGroupReadOnlyDTO(Group group) {

        GroupReadOnlyDTO dto = new GroupReadOnlyDTO();

        dto.setId(group.getId());
        dto.setTitle(group.getTitle());
        dto.setOwner(userMapper.mapToUserReadOnlyDTO(group.getOwner()));
        group.getMembers().forEach(member -> {
            dto.getMembers().add(userMapper.mapToUserReadOnlyDTO(member));
        });
        group.getMessages().forEach(message -> {
            dto.getMessages().add(messageMapper.mapToMessageReadOnlyDTO(message));
        });

        return dto;
    }

    public Group mapToGroup(GroupInsertDTO dto) {

        Group group = new Group();

        group.setTitle(dto.getTitle());
        group.setOwner(userMapper.mapToUser(dto.getOwner()));
        dto.getMembers().forEach(member -> {
            group.getMembers().add(userMapper.mapToUser(member));
        });

        return group;
    }

    public Group mapToGroup(GroupUpdateDTO dto) {

        Group group = new Group();

        dto.getMembers().forEach(member -> {
            group.getMembers().add(userMapper.mapToUser(member));
        });

        return group;
    }
}
