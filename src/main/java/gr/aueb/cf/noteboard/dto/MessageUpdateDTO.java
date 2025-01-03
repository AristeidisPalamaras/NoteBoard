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
public class MessageUpdateDTO {

    @NotNull(message = "Id can not be empty")
    private Long id;

    private Boolean isCompleted;
}
