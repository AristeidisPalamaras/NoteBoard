package gr.aueb.cf.noteboard.repository;

import gr.aueb.cf.noteboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
    //todo find Set<User> by group / or can I get them from group.getMembers()
    //todo find Set<User> by message / or can I get them from message.getViews().getUser()
}
