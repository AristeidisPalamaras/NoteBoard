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
public class MessageReadOnlyDTO {

    private Long id;
    private String text;

    private UserReadOnlyDTO author;
    private GroupReadOnlyDTO group;
    private Set<ViewsReadOnlyDTO> views;

    private Boolean isCompleted;
}
