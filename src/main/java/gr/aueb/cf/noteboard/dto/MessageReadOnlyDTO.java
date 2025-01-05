package gr.aueb.cf.noteboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageReadOnlyDTO {

    private Long id;
    private String text;
    private String author;
    private LocalDateTime createdAt;

    //    private GroupReadOnlyDTO group;
//    private Set<ReactionReadOnlyDTO> reactions;
}
