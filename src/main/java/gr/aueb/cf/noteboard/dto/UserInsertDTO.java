package gr.aueb.cf.noteboard.dto;

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
public class UserInsertDTO {

    @NotEmpty(message = "Username is required")
    @Email(message = "Invalid username")
    private String username;

    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[@#$!%&*]).{8,}$",
            message = "Invalid password")
    private String password;

    @NotEmpty(message = "Confirm password is required")
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[@#$!%&*]).{8,}$",
            message = "Invalid password")
    private String confirmPassword;
}
