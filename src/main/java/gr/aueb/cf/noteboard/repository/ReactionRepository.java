package gr.aueb.cf.noteboard.repository;

import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.User;
import gr.aueb.cf.noteboard.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long>,
        JpaSpecificationExecutor<Reaction> {
    Optional<Reaction> findByMessageAndUser(Message message, User user); //todo Do I need this
    //todo find Set<Views> by message / or can I get them with message.getViews()

}
