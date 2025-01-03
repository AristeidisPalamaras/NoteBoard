package gr.aueb.cf.noteboard.mapper;

import gr.aueb.cf.noteboard.dto.ViewsInsertDTO;
import gr.aueb.cf.noteboard.dto.ViewsReadOnlyDTO;
import gr.aueb.cf.noteboard.dto.ViewsUpdateDTO;
import gr.aueb.cf.noteboard.model.Views;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewsMapper {

    private final MessageMapper messageMapper;
    private final UserMapper userMapper;

    public ViewsReadOnlyDTO mapToViewsReadOnlyDTO(Views views) {

        ViewsReadOnlyDTO dto = new ViewsReadOnlyDTO();

        dto.setId(views.getId());
        dto.setMessage(messageMapper.mapToMessageReadOnlyDTO(views.getMessage()));
        dto.setUser(userMapper.mapToUserReadOnlyDTO(views.getUser()));
        dto.setIsRead(views.getIsRead());

        return new ViewsReadOnlyDTO();
    }

    public Views mapToViews(ViewsInsertDTO dto) {

        Views view = new Views();

        view.setMessage(messageMapper.mapToMessage(dto.getMessage()));
        view.setUser(userMapper.mapToUser(dto.getUser()));

        return view;
    }

    public Views mapToViews(ViewsUpdateDTO dto) {

        Views view = new Views();

        view.setId(dto.getId());
        view.setMessage(messageMapper.mapToMessage(dto.getMessage()));
        view.setUser(userMapper.mapToUser(dto.getUser()));

        return view;
    }
}
