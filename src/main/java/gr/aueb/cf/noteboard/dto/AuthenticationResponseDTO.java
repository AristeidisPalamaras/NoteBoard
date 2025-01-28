package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data transfer object providing information about a logged-in user.")
public class AuthenticationResponseDTO {

    @Schema(description = "The username of the logged-in user.")
    private String username;

    @Schema(description = "The Id of the logged-in user.")
    private Long userId;

    @Schema(description = "JWT token for user authentication.")
    private String token;
}
