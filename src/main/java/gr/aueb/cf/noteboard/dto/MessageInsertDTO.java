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

    @NotNull(message = "Author can not be missing")
    private UserUpdateDTO author;

    @NotNull(message = "Group can not be missing")
    private GroupUpdateDTO group;

    @NotEmpty(message = "message can not be empty")
    @Size(max = 480, message = "message must be less than 480 characters")
    private String text;
}
