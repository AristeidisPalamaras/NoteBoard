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

    @NotEmpty(message = "Name is required")
    @Size(max = 120, message = "Name must be less than 120 characters")
    private String name;

    @NotEmpty(message = "Owner is required")
    private String owner;

    private Set<String> members;
}
