package gr.aueb.cf.noteboard.core.exceptions;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ValidationException extends Exception {
    private final String code = "ValidationErrors";
    private BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }
}
