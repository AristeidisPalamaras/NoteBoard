package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.UserInsertDTO;
import gr.aueb.cf.noteboard.dto.UserLoginDTO;
import gr.aueb.cf.noteboard.dto.UserReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.UserUpdateDTO;
import gr.aueb.cf.noteboard.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final GroupMapper groupMapper;
    private final MessageMapper messageMapper;
    private final ViewsMapper viewsMapper;

//    private final PasswordEncoder passwordEncoder;

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {

        UserReadOnlyDTO dto = new UserReadOnlyDTO();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());

        user.getOwnedGroups().forEach(group -> {
           dto.getOwnedGroups().add(groupMapper.mapToGroupReadOnlyDTO(group));
        });

        user.getJoinedGroups().forEach(group -> {
            dto.getJoinedGroups().add(groupMapper.mapToGroupReadOnlyDTO(group));
        });

        user.getAuthoredMessages().forEach(message -> {
            dto.getAuthoredMessages().add(messageMapper.mapToMessageReadOnlyDTO(message));
        });

        user.getViews().forEach(view -> {
            dto.getViews().add(viewsMapper.mapToViewsReadOnlyDTO(view));
        });

        return dto;
    }

    public User mapToUser(UserInsertDTO dto) {

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); //todo Where do I compare password and confirmPassword?
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return user;
    }

    public User mapToUser(UserUpdateDTO dto) {

        User user = new User();

        user.setId(dto.getId());
        user.setUsername(dto.getUsername());

        dto.getOwnedGroups().forEach(group -> {
           user.getOwnedGroups().add(groupMapper.mapToGroup(group));
        });

        dto.getJoinedGroups().forEach(group -> {
            user.getJoinedGroups().add(groupMapper.mapToGroup(group));
        });

        dto.getAuthoredMessages().forEach(message -> {
            user.getAuthoredMessages().add(messageMapper.mapToMessage(message));
        });

        dto.getViews().forEach(view -> {
            user.getViews().add(viewsMapper.mapToViews(view));
        });

        return user;
    }

    public User mapToUser(UserLoginDTO dto) {

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));


        return user;
    }
}
