package gr.aueb.cf.noteboard.service;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.dto.GroupInsertDTO;
import gr.aueb.cf.noteboard.dto.GroupReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.GroupUpdateDTO;

import java.util.List;

public interface IGroupService {

    GroupReadOnlyDTO insertGroup(GroupInsertDTO groupInsertDTO) throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, AppObjectNotFoundException;
    GroupReadOnlyDTO updateGroup(GroupUpdateDTO groupUpdateDTO) throws AppObjectNotFoundException, AppObjectInvalidArgumentException;
    void deleteGroup(Long id) throws AppObjectNotFoundException;
    GroupReadOnlyDTO getGroupById(Long id) throws AppObjectNotFoundException;
    // GroupReadOnlyDTO getGroupByName(String name) throws AppObjectNotFoundException;
    List<GroupReadOnlyDTO> getGroupsByOwnerId(Long ownerId) throws AppObjectNotFoundException;
    List<GroupReadOnlyDTO> getGroupsByMemberId(Long memberId) throws AppObjectNotFoundException;
    boolean isOwner(Long groupId, Long userId) throws AppObjectNotFoundException;
    boolean isMember(Long groupId, Long userId) throws AppObjectNotFoundException;
}
