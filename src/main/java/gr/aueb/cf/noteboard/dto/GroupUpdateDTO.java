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

    private Set<String> removeMembers;
    private Set<String> addMembers;
}
