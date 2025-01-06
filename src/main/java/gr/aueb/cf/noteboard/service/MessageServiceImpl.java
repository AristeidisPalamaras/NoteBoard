package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.pageables.MessagePageable;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
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
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, AppObjectNotFoundException {

        Message message = new Message();
        message.setText(messageInsertDTO.getText());

        User author = userRepository.findByUsername(messageInsertDTO.getAuthor())
                .orElseThrow(() -> new AppObjectNotFoundException("User",
                        "User with username " + messageInsertDTO.getAuthor() + " not found"));
        message.setAuthor(author);
        author.addAuthoredMessage(message);

        Group group = groupRepository.findByName(messageInsertDTO.getGroup())
                .orElseThrow(() -> new AppObjectNotFoundException("Group",
                        "Group with name " + messageInsertDTO.getGroup() + " not found"));

        message.setGroup(group);
        group.addMessage(message);

        message = messageRepository.save(message);
        return messageMapper.mapToMessageReadOnlyDTO(message);
    }

    @Transactional
    public MessageReadOnlyDTO getMessageById(Long id) throws AppObjectNotFoundException {

        MessageReadOnlyDTO messageReadOnlyDTO = messageRepository.findById(id)
                .map(messageMapper::mapToMessageReadOnlyDTO)
                .orElseThrow(() -> new AppObjectNotFoundException("Message", "Message with id " + id + " not found"));

        return messageReadOnlyDTO;
    }

    @Transactional
    public Page<MessageReadOnlyDTO> getMessages(int page, Long groupId, String sortDirection, Long authorId) {

        MessagePageable messagePageable = new MessagePageable();
        messagePageable.setPage(page);

        if (sortDirection != null && !sortDirection.isBlank()) {
            messagePageable.setSortDirection(Sort.Direction.fromString(sortDirection));
        }

        if ( authorId != null ) {
            return messageRepository.findMessagesByGroupAndAuthor(groupId, authorId, messagePageable.getPageable())
                    .map(messageMapper::mapToMessageReadOnlyDTO);
        }
        return messageRepository.findMessagesByGroup(groupId, messagePageable.getPageable())
                .map(messageMapper::mapToMessageReadOnlyDTO);
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
}
