package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.MessageInsertDTO;
import gr.aueb.cf.noteboard.dto.MessageReadOnlyDTO;
import org.springframework.data.domain.Page;

public interface IMessageService {

    MessageReadOnlyDTO insertMessage(MessageInsertDTO messageInsertDTO) throws AppObjectNotFoundException;
    void deleteMessage(Long id) throws AppObjectNotFoundException;
    MessageReadOnlyDTO getMessageById(Long id) throws AppObjectNotFoundException;
    Page<MessageReadOnlyDTO> getMessagesByGroupId(int page, Long groupId, String sortDirection) throws AppObjectNotFoundException;
    Page<MessageReadOnlyDTO> getMessagesByGroupIdAndAuthorId(int page, Long groupId, Long authorId, String sortDirection) throws AppObjectNotFoundException;
    Page<MessageReadOnlyDTO> getMessagesByGroupIdAndAuthorUsernameLike(int page, Long groupId, String username, String sortDirection) throws AppObjectNotFoundException;
    boolean isAuthor(Long messageId, Long userId) throws AppObjectNotFoundException;
}
