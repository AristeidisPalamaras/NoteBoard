package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object providing information about a group")
public class GroupReadOnlyDTO {

    @Schema(description = "The unique ID number of the group.")
    private Long id;

    @Schema(description = "The name of the group.")
    private String name;

    @Schema(description = "The owner of the group.")
    private UserReadOnlyDTO owner;

    @Schema(description = "The list of group members.")
    private Set<UserReadOnlyDTO> members;

//    private Set<MessageReadOnlyDTO> messages;
}
