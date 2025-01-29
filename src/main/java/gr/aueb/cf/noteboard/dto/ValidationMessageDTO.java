package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Schema(description = "Data transfer object providing validation error information")
public class ValidationMessageDTO {

    @Schema(description = "The code of the error")
    private String code;

    @Schema(description = "A list of the validation errors")
    private Map<String, String> description;

    public ValidationMessageDTO(String code) {
        this.code = code;
        this.description = new HashMap<>();
    }
}
