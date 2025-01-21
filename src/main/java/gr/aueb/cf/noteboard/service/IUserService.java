package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.UserInsertDTO;
import gr.aueb.cf.noteboard.dto.UserReadOnlyDTO;

import java.util.List;

public interface IUserService {

    UserReadOnlyDTO insertUser(UserInsertDTO userInsertDTO) throws AppObjectAlreadyExists, AppObjectInvalidArgumentException;
    UserReadOnlyDTO getUserById(Long id) throws AppObjectNotFoundException;
    UserReadOnlyDTO getUserByUsername(String username) throws AppObjectNotFoundException;
    List<UserReadOnlyDTO> getUsersByUsernameLike(String username);
    List<UserReadOnlyDTO> getUsersByGroupIdAndUsernameLike(Long groupId, String username) throws AppObjectNotFoundException;
}
