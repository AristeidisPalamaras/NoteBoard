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
public class ViewsUpdateDTO {

    @NotNull(message = "Id can not be empty")
    private Long id;

//    private Message message;
//    private User user;

//    private Boolean isRead;
    private Set<Reaction> reactions;
}
