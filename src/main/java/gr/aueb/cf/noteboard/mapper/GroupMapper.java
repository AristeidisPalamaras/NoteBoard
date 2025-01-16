package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.GroupReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.UserReadOnlyDTO;
import gr.aueb.cf.noteboard.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class GroupMapper {

    private final UserMapper userMapper;

    public GroupReadOnlyDTO mapToGroupReadOnlyDTO(Group group) {

        GroupReadOnlyDTO dto = new GroupReadOnlyDTO();

        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setOwner(userMapper.mapToUserReadOnlyDTO(group.getOwner()));

        if (dto.getMembers() == null) {
            dto.setMembers(new HashSet<>());
        }
        if (group.getMembers() != null) {
            group.getMembers().forEach(member -> {
                dto.getMembers().add(userMapper.mapToUserReadOnlyDTO(member));
            });
        }

//        group.getMessages().forEach(message -> {
//            dto.getMessages().add(messageMapper.mapToMessageReadOnlyDTO(message));
//        });

        return dto;
    }

//    public Group mapToGroup(GroupInsertDTO dto) {
//
//        return null;
//    }

//    public Group mapToGroup(GroupUpdateDTO dto) {
//
//        return null;
//    }
}
