package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessageDTO {

    private String code;
    private String description;

    public ResponseMessageDTO(String code) {
        this.code = code;
        this.description = "";
    }
}
