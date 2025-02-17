package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.pageables.MessagePageable;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.MessageInsertDTO;
import gr.aueb.cf.noteboard.dto.MessageReadOnlyDTO;
import gr.aueb.cf.noteboard.mapper.MessageMapper;
import gr.aueb.cf.noteboard.model.Group;
import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.Reaction;
import gr.aueb.cf.noteboard.model.User;
import gr.aueb.cf.noteboard.repository.GroupRepository;
import gr.aueb.cf.noteboard.repository.MessageRepository;
import gr.aueb.cf.noteboard.repository.ReactionRepository;
import gr.aueb.cf.noteboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ReactionRepository reactionRepository;


    @Transactional
    public MessageReadOnlyDTO insertMessage(MessageInsertDTO messageInsertDTO)
            throws AppObjectNotFoundException {

        Message message = new Message();
        message.setText(messageInsertDTO.getText());

        User author = userRepository.findUserByUsername(messageInsertDTO.getAuthor())
                .orElseThrow(() -> new AppObjectNotFoundException("User",
                        "User with name " + messageInsertDTO.getAuthor() + " not found"));
        message.setAuthor(author);
        // author.addAuthoredMessage(message);

        Group group = groupRepository.findGroupById(messageInsertDTO.getGroupId())
                .orElseThrow(() -> new AppObjectNotFoundException("Group",
                        "Group with id " + messageInsertDTO.getGroupId() + " not found"));

        message.setGroup(group);
        // group.addMessage(message);

        message = messageRepository.save(message);
        return messageMapper.mapToMessageReadOnlyDTO(message);
    }

    @Transactional
    public void deleteMessage(Long id) throws AppObjectNotFoundException {

        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new AppObjectNotFoundException("Message", "Message with id " + id + " not found"));

        User author = message.getAuthor();
        if (author != null) {
            author.removeAuthoredMessage(message);
        }

        Group group = message.getGroup();
        if (group != null) {
            group.removeMessage(message);
        }

        Set<Reaction> reactions = message.getReactions();
        if (reactions != null) {
            reactions.forEach(reaction -> {
                reactionRepository.delete(reaction);
            });
        }
        messageRepository.delete(message);
    }

    @Transactional
    public MessageReadOnlyDTO getMessageById(Long id) throws AppObjectNotFoundException {

        MessageReadOnlyDTO messageReadOnlyDTO = messageRepository.findMessageById(id)
                .map(messageMapper::mapToMessageReadOnlyDTO)
                .orElseThrow(() -> new AppObjectNotFoundException("Message", "Message with id " + id + " not found"));

        return messageReadOnlyDTO;
    }

    @Transactional
    public Page<MessageReadOnlyDTO> getMessagesByGroupId(int page, Long groupId, String sortDirection)
            throws AppObjectNotFoundException {

        groupRepository.findGroupById(groupId)
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + groupId + " not found"));

        Pageable pageable = getPageable(page, sortDirection);

            return messageRepository.findMessagesByGroupId(groupId, pageable)
                    .map(messageMapper::mapToMessageReadOnlyDTO);
    }

    @Transactional
    public Page<MessageReadOnlyDTO> getMessagesByGroupIdAndAuthorId(int page, Long groupId, Long authorId, String sortDirection)
            throws AppObjectNotFoundException {

        Pageable pageable = getPageable(page, sortDirection);

        groupRepository.findGroupById(groupId)
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + groupId + " not found"));

        userRepository.findUserById(authorId)
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with id " + authorId + " not found"));

        return messageRepository.findMessagesByGroupIdAndAuthorId(groupId, authorId, pageable)
                    .map(messageMapper::mapToMessageReadOnlyDTO);
    }

    @Transactional
    public Page<MessageReadOnlyDTO> getMessagesByGroupIdAndAuthorUsernameLike(int page, Long groupId, String username, String sortDirection)
            throws AppObjectNotFoundException {

        Pageable pageable = getPageable(page, sortDirection);

        groupRepository.findGroupById(groupId)
                .orElseThrow(() -> new AppObjectNotFoundException("Group", "Group with id " + groupId + " not found"));

        if ( username == null || username.isBlank() ) {
            return messageRepository.findMessagesByGroupId(groupId, pageable)
                    .map(messageMapper::mapToMessageReadOnlyDTO);
        }

        return messageRepository.findMessagesByGroupIdAndAuthorUsernameLike(groupId, username, pageable)
                .map(messageMapper::mapToMessageReadOnlyDTO);
    }

    public boolean isAuthor(Long messageId, Long userId) throws AppObjectNotFoundException {

        messageRepository.findMessageById(messageId)
                .orElseThrow(() -> new AppObjectNotFoundException("Message", "Message with id " + messageId + " not found"));

        userRepository.findUserById(userId)
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with id " + userId + " not found"));

        return messageRepository.countByMessageIdAndAuthorId(messageId, userId) > 0;
    }

    private Pageable getPageable(int page, String sortDirection) {
        MessagePageable messagePageable = new MessagePageable();
        messagePageable.setPage(page);
        if (sortDirection != null && !sortDirection.isBlank()) {
            messagePageable.setSortDirection(Sort.Direction.fromString(sortDirection));
        }
        return messagePageable.getPageable();
    }
}
