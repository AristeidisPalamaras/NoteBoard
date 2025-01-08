package gr.aueb.cf.noteboard.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
