package gr.aueb.cf.noteboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationMessageDTO {
    private String code;
    private Map<String, String> description;

    public ValidationMessageDTO(String code) {
        this.code = code;
        this.description = new HashMap<>();
    }
}
