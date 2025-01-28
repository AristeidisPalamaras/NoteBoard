package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.core.enums.ReactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object providing information about a reaction.")
public class ReactionReadOnlyDTO {

    @Schema(description = "The unique ID number of the reaction.")
    private Long id;

    @Schema(description = "The username of the user that posted the reaction.")
    private String user;

    @Schema(description = "The type of reaction.")
    private ReactionType description;

    //    private MessageReadOnlyDTO message;
}
