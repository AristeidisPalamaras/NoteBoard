package gr.aueb.cf.noteboard.dto;

import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.User;
import gr.aueb.cf.noteboard.model.static_data.Reaction;
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
public class ViewsInsertDTO {

    @NotNull(message = "Message can not be missing")
    private MessageUpdateDTO message;

    @NotNull(message = "User can not be missing")
    private UserUpdateDTO user;

//    private Set<Reaction> reactions; //todo can't fingure that out
}
