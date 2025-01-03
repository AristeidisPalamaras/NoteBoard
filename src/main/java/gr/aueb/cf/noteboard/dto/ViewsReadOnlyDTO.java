package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.model.static_data.Reaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViewsReadOnlyDTO {

    private Long id;
    private MessageReadOnlyDTO message;
    private UserReadOnlyDTO user;
    private Boolean isRead;
//    private Set<Reaction> reactions; //todo Can't figure that out
}
