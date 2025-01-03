package gr.aueb.cf.noteboard.model.static_data;

import gr.aueb.cf.noteboard.model.Views;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany
    @JoinTable(name = "reaction_view",
            joinColumns = @JoinColumn(name = "reaction_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "view_id", referencedColumnName = "id"))
    private Set<Views> views = new HashSet<>();

    public void addView(Views view) {
        if (views == null) views = new HashSet<>();
        views.add(view);
        view.getReactions().add(this);
    }

    public void removeView(Views view) {
        views.remove(view);
        view.getReactions().remove(this);
    }
}
