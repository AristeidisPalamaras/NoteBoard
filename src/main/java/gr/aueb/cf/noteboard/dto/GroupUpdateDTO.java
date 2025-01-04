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

    @NotNull(message = "Id can not be empy")
    private Long id;

    private String name;

    private Set<UserUpdateDTO> members;
}
