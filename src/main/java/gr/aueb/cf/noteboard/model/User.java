package gr.aueb.cf.noteboard.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<Group> ownedGroups = new HashSet<>();

    @ManyToMany(mappedBy = "members")
    private Set<Group> joinedGroups = new HashSet<>();

    @OneToMany(mappedBy = "author")
    private Set<Message> authoredMessages = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Reaction> reactions = new HashSet<>();

    @PrePersist
    public void initialize() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
    }

    public void addOwnedGroup(Group group) {
        if (ownedGroups == null) ownedGroups = new HashSet<>();
        ownedGroups.add(group);
        group.setOwner(this);
    }

    public void removeOwnedGroup(Group group) {
        ownedGroups.remove(group);
        group.setOwner(null);
    }

    public void addJoinedGroup(Group group) {
        if (joinedGroups == null) joinedGroups = new HashSet<>();
        joinedGroups.add(group);
        group.getMembers().add(this);
    }

    public void removeJoinedGroup(Group group) {
        joinedGroups.remove(group);
        group.getMembers().remove(this);
    }

    public void addAuthoredMessage(Message message) {
        if (authoredMessages == null) authoredMessages = new HashSet<>();
        authoredMessages.add(message);
        message.setAuthor(this);
    }

    public void removeAuthoredMessage(Message message) {
        authoredMessages.remove(message);
        message.setAuthor(null);
    }

    public void addReaction(Reaction reaction) {
        if (reactions == null) reactions = new HashSet<>();
        reactions.add(reaction);
        reaction.setUser(this);
    }

    public void removeReaction(Reaction reaction) {
        reactions.remove(reaction);
        reaction.setUser(null);
    }
}
