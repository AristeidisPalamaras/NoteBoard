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
//@Builder
@Table(name = "groups")
public class Group extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @ManyToMany
    @JoinTable(name = "group_user",
        joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "group")
    private Set<Message> messages;

    @PrePersist
    public void initialize() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
    }

    public void addMember(User user) {
        if (members == null) members = new HashSet<>();
        members.add(user);
        user.getJoinedGroups().add(this);
    }

    public void removeMember(User user) {
        members.remove(user);
        user.getJoinedGroups().remove(this);
    }

    public void addMessage(Message message) {
        if (messages == null) messages = new HashSet<>();
        messages.add(message);
        message.setGroup(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setGroup(null);
    }
}
