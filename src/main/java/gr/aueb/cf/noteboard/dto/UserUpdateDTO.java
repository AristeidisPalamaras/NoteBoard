package gr.aueb.cf.noteboard.dto;

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

    private Set<GroupInsertDTO> ownedGroups;
    private Set<GroupInsertDTO> joinedGroups;
    private Set<MessageInsertDTO> authoredMessages;
    private Set<ViewsInsertDTO> views;
}
