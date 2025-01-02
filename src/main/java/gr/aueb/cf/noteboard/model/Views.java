package gr.aueb.cf.noteboard.model;

import gr.aueb.cf.noteboard.core.enums.ReactionType;
import jakarta.persistence.*;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    private ReactionType reaction;

    @PrePersist
    public void initialize() {
        if (isRead == null) {
            isRead = false;
        }
    }
}
