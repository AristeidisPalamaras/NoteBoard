package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object for message creation.")
public class MessageInsertDTO {

    @NotEmpty(message = "Author is required")
    @Schema(description = "The username of the message author.")
    private String author;

    @NotNull(message = "Group is required")
    @Schema(description = "The ID of the group to which the new message will be posted.")
    private Long groupId;

    @NotEmpty(message = "Message can not be empty")
    @Size(max = 240, message = "Message must be less than 240 characters")
    @Schema(description = "The text of the message.")
    private String text;
}
