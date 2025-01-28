package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object providing information about a message.")
public class MessageReadOnlyDTO {

    @Schema(description = "The unique ID number of the message.")
    private Long id;

    @Schema(description = "The text of the message.")
    private String text;

    @Schema(description = "The author of the message")
    private UserReadOnlyDTO author;

    @Schema(description = "The date/time that the message was posted.")
    private LocalDateTime createdAt;

//    private GroupReadOnlyDTO group;
//    private Set<ReactionReadOnlyDTO> reactions;
}
