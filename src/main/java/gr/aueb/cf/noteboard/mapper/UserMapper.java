package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.UserInsertDTO;
import gr.aueb.cf.noteboard.dto.UserLoginDTO;
import gr.aueb.cf.noteboard.dto.UserReadOnlyDTO;
import gr.aueb.cf.noteboard.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

//    private final PasswordEncoder passwordEncoder;

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {

        UserReadOnlyDTO dto = new UserReadOnlyDTO();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());

        return dto;
    }

    public User mapToUser(UserInsertDTO dto) {

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); //todo Where do I compare password and confirmPassword?
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));

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
