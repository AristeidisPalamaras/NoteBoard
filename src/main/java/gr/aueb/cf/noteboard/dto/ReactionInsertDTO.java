package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.core.enums.ReactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object for reaction creation.")
public class ReactionInsertDTO {

    @NotNull(message = "Message is required")
    @Schema(description = "The ID of the message that is reacted at.")
    private Long messageId;

    @NotEmpty(message = "User is required")
    @Schema(description = "The username of the user that is reacting to a message.")
    private String user;

    @NotNull(message = "Description is required")
    @Schema(description = "The type of reaction.")
    private ReactionType description;
}
