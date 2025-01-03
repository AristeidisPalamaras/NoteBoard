package gr.aueb.cf.noteboard.repository;

import gr.aueb.cf.noteboard.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface GroupRepositoy extends JpaRepository<Group, Long>,
        JpaSpecificationExecutor<Group> {
    Optional<Group> findByTitle(String title); //todo Do I need this?
    //todo find Set<Group> by owner / or can I get it with user.getOwnedGroups
    //todo find Set<Group> by member / pr can I get it with user.getJoinedGroups
}
