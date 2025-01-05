package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.core.enums.ReactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReactionInsertDTO {

    @NotNull(message = "The message is required")
    private Long messageId;

    @NotNull(message = "The user is required")
    private String user;

    @NotNull(message = "The escription is required")
    private ReactionType description;
}
