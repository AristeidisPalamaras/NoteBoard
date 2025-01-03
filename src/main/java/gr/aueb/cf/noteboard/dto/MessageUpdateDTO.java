package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.model.Group;
import gr.aueb.cf.noteboard.model.User;
import gr.aueb.cf.noteboard.model.Views;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageUpdateDTO {

    @NotNull(message = "Id can not be empty")
    private Long id;

//    private String text;

//    private User author;
//    private Group group;

    private Set<Views> views;

    private Boolean isCompleted;
}
