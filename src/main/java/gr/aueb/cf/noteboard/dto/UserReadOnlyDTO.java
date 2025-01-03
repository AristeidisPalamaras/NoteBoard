package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.model.Group;
import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.Views;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO {

    private Long id;
    private String username;

    private Set<Group> ownedGroups;
    private Set<Group> joinedGroups;
    private Set<Message> authoredMessages;
    private Set<Views> views;
}
