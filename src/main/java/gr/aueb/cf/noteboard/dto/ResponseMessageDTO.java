package gr.aueb.cf.noteboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Data transfer object providing error information")
public class ResponseMessageDTO {

    @Schema(description = "The code of the error")
    private String code;

    @Schema(description = "The description of the error")
    private String description;

    public ResponseMessageDTO(String code) {
        this.code = code;
        this.description = "";
    }
}
