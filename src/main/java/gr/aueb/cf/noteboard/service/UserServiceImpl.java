package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.UserInsertDTO;
import gr.aueb.cf.noteboard.dto.UserReadOnlyDTO;
import gr.aueb.cf.noteboard.mapper.UserMapper;
import gr.aueb.cf.noteboard.model.User;
import gr.aueb.cf.noteboard.repository.GroupRepository;
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
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserMapper userMapper;

    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO insertUser(UserInsertDTO userInsertDTO) throws AppObjectAlreadyExists, AppObjectInvalidArgumentException {

        if (!isPasswordMatch(userInsertDTO))
            throw new AppObjectInvalidArgumentException("Validation", "Password and confirmPassword must match");

        if (userRepository.findUserByUsername(userInsertDTO.getUsername()).isPresent()) {
            throw new AppObjectAlreadyExists("User", "User with name " + userInsertDTO.getUsername() + " already exists");
        }

        User user = userRepository.save(userMapper.mapToUser(userInsertDTO));

        return userMapper.mapToUserReadOnlyDTO(user);
    }

    @Transactional
    public UserReadOnlyDTO getUserById(Long id) throws AppObjectNotFoundException {

        UserReadOnlyDTO userReadOnlyDTO = userRepository.findUserById(id)
                .map(userMapper::mapToUserReadOnlyDTO)
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with id " + id + " not found"));

        return userReadOnlyDTO;
    }

    @Transactional
    public UserReadOnlyDTO getUserByUsername(String username) throws AppObjectNotFoundException {

        UserReadOnlyDTO userReadOnlyDTO = userRepository.findUserByUsername(username)
                .map(userMapper::mapToUserReadOnlyDTO)
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with name " + username + " not found"));

        return userReadOnlyDTO;
    }

    @Transactional
    public List<UserReadOnlyDTO> getUsersByUsernameLike(String username) {

        if (username == null || username.isBlank()) {
            return userRepository.findAll()
                    .stream()
                    .map(userMapper::mapToUserReadOnlyDTO)
                    .collect(Collectors.toList());
        }

        return userRepository.findUsersByUsernameLike(username.trim())
                .stream()
                .map(userMapper::mapToUserReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<UserReadOnlyDTO> getUsersByGroupIdAndUsernameLike(Long groupId, String username) throws AppObjectNotFoundException {

        groupRepository.findGroupById(groupId)
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + groupId + " not found"));


        if (username == null || username.isBlank()) {
            return userRepository.findUsersByGroupId(groupId)
                    .stream()
                    .map(userMapper::mapToUserReadOnlyDTO)
                    .collect(Collectors.toList());
        }

        return userRepository.findUsersByGroupIdAndUsernameLike(groupId, username.trim())
                .stream()
                .map(userMapper::mapToUserReadOnlyDTO)
                .collect(Collectors.toList());
    }

    private boolean isPasswordMatch(UserInsertDTO userInsertDTO) {

        return userInsertDTO.getPassword().equals(userInsertDTO.getConfirmPassword());
    }
}
