package gr.aueb.cf.noteboard.dto;

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
public class MessageInsertDTO {

    @NotEmpty(message = "Author is required")
    private String author;

    @NotNull(message = "Group is required")
    private Long groupId;

    @NotEmpty(message = "Message can not be empty")
    @Size(max = 480, message = "Message must be less than 480 characters")
    private String text;
}
