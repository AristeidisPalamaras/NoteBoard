package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.GroupInsertDTO;
import gr.aueb.cf.noteboard.dto.GroupReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.GroupUpdateDTO;
import gr.aueb.cf.noteboard.mapper.GroupMapper;
import gr.aueb.cf.noteboard.model.Group;
import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.User;
import gr.aueb.cf.noteboard.repository.GroupRepository;
import gr.aueb.cf.noteboard.repository.MessageRepository;
import gr.aueb.cf.noteboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements IGroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Transactional(rollbackOn = Exception.class)
    public GroupReadOnlyDTO insertGroup(GroupInsertDTO groupInsertDTO)
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, AppObjectNotFoundException {

        Group group = new Group();
        group.setName(groupInsertDTO.getName());
        User owner = userRepository.findUserByUsername(groupInsertDTO.getOwner())
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with name " + groupInsertDTO.getOwner() + " not found"));

        group.setOwner(owner);
        owner.addOwnedGroup(group);

        for (String username : groupInsertDTO.getMembers()) {
            User member = userRepository.findUserByUsername(username)
                    .orElseThrow(() -> new AppObjectNotFoundException("User", "User with name " + username + " not found"));
            group.addMember(member);
        }

        group = groupRepository.save(group);

        return groupMapper.mapToGroupReadOnlyDTO(group);
    }

    @Transactional
    public GroupReadOnlyDTO getGroupById(Long id) throws AppObjectNotFoundException {

        GroupReadOnlyDTO groupReadOnlyDTO = groupRepository.findGroupById(id)
                .map(groupMapper::mapToGroupReadOnlyDTO)
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + id + " not found"));

        return groupReadOnlyDTO;
    }

    @Transactional
    public GroupReadOnlyDTO getGroupByName(String name) throws AppObjectNotFoundException {

        GroupReadOnlyDTO groupReadOnlyDTO = groupRepository.findGroupByName(name)
                .map(groupMapper::mapToGroupReadOnlyDTO)
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with name " + name + " not found"));

        return groupReadOnlyDTO;
    }

    @Transactional
    public List<GroupReadOnlyDTO> getGroupsByOwnerId(Long ownerId) {

        List<GroupReadOnlyDTO> groups;

        groups = groupRepository.findGroupsByOwnerId(ownerId)
                .stream()
                .map(groupMapper::mapToGroupReadOnlyDTO)
                .collect(Collectors.toList());

        return groups;
    }

    @Transactional
    public List<GroupReadOnlyDTO> getGroupsByMemberId(Long memberId) {

        List<GroupReadOnlyDTO> groups;

        groups = groupRepository.findGroupsByMemberId(memberId)
                .stream()
                .map(groupMapper::mapToGroupReadOnlyDTO)
                .collect(Collectors.toList());

        return groups;
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteGroup(Long id) throws AppObjectNotFoundException {

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + id + " not found"));

        User owner = group.getOwner();
        if (owner != null) {
                owner.removeOwnedGroup(group);
        }

        Set<User> members = group.getMembers();
        if (members != null) {
            members.forEach(member -> member.removeJoinedGroup(group));
        }

        Set<Message> messages = group.getMessages();
        if (messages != null) {
            messages.forEach(message -> {
                messageRepository.delete(message);
            });
        }

        groupRepository.delete(group);
    }

    @Transactional(rollbackOn = Exception.class)
    public GroupReadOnlyDTO updateGroup(GroupUpdateDTO groupUpdateDTO)
            throws AppObjectNotFoundException, AppObjectInvalidArgumentException {

        Group group = groupRepository.findById(groupUpdateDTO.getId())
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + groupUpdateDTO.getId() + " not found"));

        for (String username : groupUpdateDTO.getMembers()) {
            User member = userRepository.findUserByUsername(username)
                    .orElseThrow(() -> new AppObjectNotFoundException("User", "User with name " + username + " not found"));
            if (!group.getMembers().contains(member)) {
                group.addMember(member);
            } else {
                group.removeMember(member);
            }
        }

        group = groupRepository.save(group);

        return groupMapper.mapToGroupReadOnlyDTO(group);
    }
}
