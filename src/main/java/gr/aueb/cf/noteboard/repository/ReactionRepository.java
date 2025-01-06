package gr.aueb.cf.noteboard.repository;

import gr.aueb.cf.noteboard.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ReactionRepository extends JpaRepository<Reaction, Long>,
        JpaSpecificationExecutor<Reaction> {

    @Query("SELECT r FROM Reaction r JOIN r.message m WHERE m.id = :messageId")
    Set<Reaction> findReactionsByMessageId(@Param("messageId") Long messageId);

    @Query("Select r FROM Reaction r JOIN r.message m JOIN r.user u WHERE m.id = :messageId AND u.id = :userId")
    Set<Reaction> findReactionsByMessageIdAndUserId(@Param("messageId") Long messageId,@Param("userId") Long userId);
}
