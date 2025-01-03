package gr.aueb.cf.noteboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO {

    private Long id;
    private String username;

    private Set<GroupReadOnlyDTO> ownedGroups;
    private Set<GroupReadOnlyDTO> joinedGroups;
    private Set<MessageReadOnlyDTO> authoredMessages;
    private Set<ViewsReadOnlyDTO> views;
}
