package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.MessageInsertDTO;
import gr.aueb.cf.noteboard.dto.MessageReadOnlyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMessageService {

    MessageReadOnlyDTO insertMessage(MessageInsertDTO messageInsertDTO) throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, AppObjectNotFoundException;
    MessageReadOnlyDTO getMessageById(Long id) throws AppObjectNotFoundException;
    Page<MessageReadOnlyDTO> getMessagesByAuthorId(int page, Long groupId, String sortDirection, Long authorId) throws AppObjectNotFoundException;
    Page<MessageReadOnlyDTO> getMessagesByAuthorUsernameLike(int page, Long groupId, String sortDirection, String username);
    void deleteMessage(Long id) throws AppObjectNotFoundException;
}
