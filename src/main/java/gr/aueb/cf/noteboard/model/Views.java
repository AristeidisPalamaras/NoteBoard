package gr.aueb.cf.noteboard.model;

import gr.aueb.cf.noteboard.model.static_data.Reaction;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "message_user")
public class Views extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Boolean isRead;

    @ManyToMany(mappedBy = "views")
    private Set<Reaction> reactions = new HashSet<>();

    @PrePersist
    public void initialize() {
        if (isRead == null) {
            isRead = false;
        }
    }

    public void addReaction(Reaction reaction) {
        if (reactions == null) reactions = new HashSet<>();
        reactions.add(reaction);
        reaction.getViews().add(this);
    }

    public void removeReaction(Reaction reaction) {
        reactions.remove(reaction);
        reaction.getViews().remove(this);
    }
}
