package gr.aueb.cf.noteboard.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {
    private String username;
    private Long userId;
    private String token;
}
