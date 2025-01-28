package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Data transfer object for user creation")
public class UserInsertDTO {

    @NotEmpty(message = "Username is required")
    @Email(message = "Invalid username")
    @Schema(description = "Username of the new user. Uses email format.")
    private String username;

    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[@#$!%&*]).{8,}$",
            message = "Invalid password")
    @Schema(description = "Password for the new user. At least 8 characters, with at least 1 capital, 1 number and 1 special character")
    private String password;

    @NotEmpty(message = "Confirm password is required")
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[@#$!%&*]).{8,}$",
            message = "Invalid password")
    @Schema(description = "Confirm password for the new user")
    private String confirmPassword;
}
