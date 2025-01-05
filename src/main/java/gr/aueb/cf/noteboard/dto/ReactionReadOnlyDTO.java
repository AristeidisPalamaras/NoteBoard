package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.core.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReactionReadOnlyDTO {

    private Long id;
    private String user;
    private ReactionType description;

    //    private MessageReadOnlyDTO message;
}
