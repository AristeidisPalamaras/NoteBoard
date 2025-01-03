package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.model.Group;
import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.Views;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {

    @NotNull(message = "Id can not be empty")
    private Long id;

    @NotEmpty(message = "Username can not be empty")
    private String username;

    private Set<Group> ownedGroups;
    private Set<Group> joinedGroups;
    private Set<Message> authoredMessages;
    private Set<Views> views;
}
