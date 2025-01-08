package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.MessageInsertDTO;
import gr.aueb.cf.noteboard.dto.MessageReadOnlyDTO;
import org.springframework.data.domain.Page;

public interface IMessageService {

    MessageReadOnlyDTO insertMessage(MessageInsertDTO messageInsertDTO) throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, AppObjectNotFoundException;
    void deleteMessage(Long id) throws AppObjectNotFoundException;
    MessageReadOnlyDTO getMessageById(Long id) throws AppObjectNotFoundException;
    Page<MessageReadOnlyDTO> getMessagesByGroupId(int page, Long groupId, String sortDirection) throws AppObjectNotFoundException;
    Page<MessageReadOnlyDTO> getMessagesByGroupIdAndAuthorId(int page, Long groupId, Long authorId, String sortDirection) throws AppObjectNotFoundException;
    Page<MessageReadOnlyDTO> getMessagesByGroupIdAndAuthorUsernameLike(int page, Long groupId, String username, String sortDirection);
    boolean isAuthor(Long messageId, Long userId);
}
