package gr.aueb.cf.noteboard.core.pageables;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class MessagePageable {
    private final static int PAGE_SIZE = 12;
    private static final String SORT_COLUMN = "createdAt";
    private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;

    private int page;
    private Sort.Direction sortDirection;

    public Sort.Direction getSortDirection(){
        if (this.sortDirection == null) return DEFAULT_SORT_DIRECTION;
        return this.sortDirection;
    }

    public Sort getSort(){
        return Sort.by(this.getSortDirection(), this.SORT_COLUMN);
    }

    public Pageable getPageable() {
        return PageRequest.of(page, PAGE_SIZE, getSort());
    }
}
