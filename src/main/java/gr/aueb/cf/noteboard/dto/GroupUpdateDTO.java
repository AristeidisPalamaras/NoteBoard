package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object for group updates.")
public class GroupUpdateDTO {

    @NotNull(message = "Id is required")
    @Schema(description = "The unique ID number of the group")
    private Long id;

    @Schema(description = "A list of usernames of groups members to be removed.")
    private Set<String> removeMembers;

    @Schema(description = "A list of usernames to be added as group members.")
    private Set<String> addMembers;
}
