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

import java.util.HashSet;
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

        if (owner.getOwnedGroups().stream().map(Group::getName).collect(Collectors.toSet()).contains(group.getName())) {
            throw new AppObjectAlreadyExists("Group", "Group with name " + groupInsertDTO.getName() + " already exists");
        }

        group.setOwner(owner);
        owner.addOwnedGroup(group);

        for (String username : groupInsertDTO.getMembers()) {
            User member = userRepository.findUserByUsername(username)
                    .orElseThrow(() -> new AppObjectNotFoundException("User", "User with name " + username + " not found"));

            if (member.getId().equals(owner.getId())) {
                throw new AppObjectInvalidArgumentException("User", "The owner of the group can not be added as a member");
            }

            group.addMember(member);
        }

        group = groupRepository.save(group);

        return groupMapper.mapToGroupReadOnlyDTO(group);
    }

    @Transactional(rollbackOn = Exception.class)
    public GroupReadOnlyDTO updateGroup(GroupUpdateDTO groupUpdateDTO)
            throws AppObjectNotFoundException, AppObjectInvalidArgumentException {

        Group group = groupRepository.findGroupById(groupUpdateDTO.getId())
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + groupUpdateDTO.getId() + " not found"));

        if (groupUpdateDTO.getRemoveMembers() != null) {
            for (String username : groupUpdateDTO.getRemoveMembers()) {
                User member = userRepository.findUserByGroupIdAndUsername(groupUpdateDTO.getId(), username)
                        .orElseThrow(() -> new AppObjectNotFoundException("User", "User with name " + username + " not found"));

                group.removeMember(member);
            }
        }

        if (groupUpdateDTO.getAddMembers() != null) {
            for (String username : groupUpdateDTO.getAddMembers()) {
                User member = userRepository.findUserByUsername(username)
                        .orElseThrow(() -> new AppObjectNotFoundException("User", "User with name " + username + " not found"));

                if (member.getId().equals(group.getOwner().getId())) {
                    throw new AppObjectInvalidArgumentException("User", "The owner of the group can not be added as a member");
                }

                if (group.getMembers().contains(member)) {
                    throw new AppObjectInvalidArgumentException("User", "User with name " + username + " is already in the group");
                }

                group.addMember(member);
            }
        }

        group = groupRepository.save(group);

        return groupMapper.mapToGroupReadOnlyDTO(group);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteGroup(Long id) throws AppObjectNotFoundException {

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + id + " not found"));

        User owner = group.getOwner();
        if (owner != null) {
            owner.removeOwnedGroup(group);
        }

        Set<User> members = new HashSet<>(group.getMembers());
        if (members != null) {
            members.forEach(member -> member.removeJoinedGroup(group));
        }

        Set<Message> messages = new HashSet<>(group.getMessages());
        if (messages != null) {
            messages.forEach(message -> {
                messageRepository.delete(message);
            });
        }

        groupRepository.delete(group);
    }

    @Transactional
    public GroupReadOnlyDTO getGroupById(Long id) throws AppObjectNotFoundException {

        GroupReadOnlyDTO groupReadOnlyDTO = groupRepository.findGroupById(id)
                .map(groupMapper::mapToGroupReadOnlyDTO)
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + id + " not found"));

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

    public boolean isOwner(Long groupId, Long userId) {
        return groupRepository.countByGroupIdAndOwnerId(groupId, userId) > 0;
    }


    public boolean isMember(Long groupId, Long userId) {
        return groupRepository.countByGroupIdAndMemberId(groupId, userId) > 0;
    }

}
