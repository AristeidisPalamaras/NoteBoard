package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data transfer object for user log in.")
public class AuthenticationRequestDTO {

    @NotEmpty(message = "Username is required")
    @Schema(description = "The username of the user attempting to log in.")
    private String username;

    @NotEmpty(message = "Password is required")
    @Schema(description = "The password of the user attempting to log in.")
    private String password;
}
