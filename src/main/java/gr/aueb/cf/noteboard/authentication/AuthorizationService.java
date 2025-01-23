package gr.aueb.cf.noteboard.authentication;

import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.noteboard.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.noteboard.service.GroupServiceImpl;
import gr.aueb.cf.noteboard.service.MessageServiceImpl;
import gr.aueb.cf.noteboard.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final UserServiceImpl userService;
    private final GroupServiceImpl groupService;
    private final MessageServiceImpl messageService;

    public void isPrincipalOrThrow(Long userId, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        userService.getUserById(userId);

        Long principalId = userService.getUserByUsername(principal.getName()).getId();

        if (!userId.equals(principalId)) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + principalId + " not authorized");
        }
    }

    public void isPrincipalOrThrow(String username, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        Long userId = userService.getUserByUsername(username).getId();
        Long principalId = userService.getUserByUsername(principal.getName()).getId();

        if (!userId.equals(principalId)) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + principalId + " not authorized");
        }
    }

    public void isOwnerOrThrow(Long groupId, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        Long userId = userService.getUserByUsername(principal.getName()).getId();

        if (!groupService.isOwner(groupId, userId) ) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + userId + " not authorized");
        }
    }

    public void isOwnerOrMemberOrThrow(Long groupId, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        Long userId = userService.getUserByUsername(principal.getName()).getId();

        if (!groupService.isOwner(groupId, userId) && !groupService.isMember(groupId, userId) ) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + userId + " not authorized");
        }
    }

    public void isAuthorOrThrow(Long messageId, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {

        Long userId = userService.getUserByUsername(principal.getName()).getId();

        if (!messageService.isAuthor(messageId, userId) ) {
            throw new AppObjectNotAuthorizedException("User", "User with id " + userId + " not authorized");
        }
    }

}
