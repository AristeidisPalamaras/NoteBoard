package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object providing information about the owned and joined groups of a user")
public class GroupsByUserReadOnlyDTO {

    @Schema(description = "The list of owned groups.")
    private List<GroupReadOnlyDTO> ownedGroups;

    @Schema(description = "The list of joined groups.")
    private List<GroupReadOnlyDTO> joinedGroups;
}
