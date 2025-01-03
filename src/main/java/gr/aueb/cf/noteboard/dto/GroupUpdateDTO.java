package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.User;
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
public class GroupUpdateDTO {

    @NotNull(message = "Id can not be empy")
    private Long id;

//    private String title;
//    private User owner;

    private Set<User> members;
    private Set<Message> messages;
}
