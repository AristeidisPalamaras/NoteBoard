package gr.aueb.cf.noteboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupReadOnlyDTO {

    private Long id;
    private String title;
    private UserReadOnlyDTO owner;
    private Set<UserReadOnlyDTO> members;
    private Set<MessageReadOnlyDTO> messages;
}
