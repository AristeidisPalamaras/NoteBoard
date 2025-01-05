package gr.aueb.cf.noteboard.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupUpdateDTO {

    @NotNull(message = "Id is required")
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    private Set<String> members;
}
