package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object providing information about a user")
public class UserReadOnlyDTO {

    @Schema(description = "The unique ID number of the user")
    private Long id;
    @Schema(description = "The username of the user")
    private String username;

//    private Set<GroupReadOnlyDTO> ownedGroups;
//    private Set<GroupReadOnlyDTO> joinedGroups;
//    private Set<MessageReadOnlyDTO> authoredMessages;
//    private Set<ReactionReadOnlyDTO> reactions;
}
