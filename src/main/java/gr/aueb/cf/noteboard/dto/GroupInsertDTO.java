package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object for group creation.")
public class GroupInsertDTO {

    @NotEmpty(message = "Name is required")
    @Size(max = 40, message = "Name must be less than 40 characters")
    @Schema(description = "The name of the group. A user cannot create multiple groups of the same name, but the same group name can exist across multiple users.")
    private String name;

    @NotEmpty(message = "Owner is required")
    @Schema(description = "The username of the group owner.")
    private String owner;

    @Schema(description = "A list of usernames to be added as group members.")
    private Set<String> members;
}
