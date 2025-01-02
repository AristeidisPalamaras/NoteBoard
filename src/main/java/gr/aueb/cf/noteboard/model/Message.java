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
@Table(name = "messages")
public class Message extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "message")
    private Set<Views> views = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    private String text;

    private Boolean isCompleted;

    @PrePersist
    public void initialize() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        if (isCompleted == null) {
            isCompleted = false;
        }
    }

    public void addView(Views view) {
        if (views == null) views = new HashSet<>();
        views.add(view);
        view.setMessage(this);
    }

    public void removeView(Views view) {
        views.remove(view);
        view.setMessage(null);
    }
}
