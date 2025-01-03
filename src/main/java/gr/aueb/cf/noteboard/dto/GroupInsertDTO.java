package gr.aueb.cf.noteboard.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class GroupInsertDTO {

    @NotEmpty(message = "title can not be empty")
    @Size(max = 120, message = "title must be less than 120 characters")
    private String title;

    @NotNull(message = "Owner can not be missing")
    private UserUpdateDTO owner;

    private Set<UserUpdateDTO> members;
}
