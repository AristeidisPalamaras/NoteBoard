package gr.aueb.cf.noteboard.repository;

import gr.aueb.cf.noteboard.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long>,
        JpaSpecificationExecutor<Message> {

    Optional<Message> findMessageById(long id);

    @Query("SELECT m FROM Message m JOIN m.group g WHERE g.id = :groupId")
    Page<Message> findMessagesByGroupId(@Param("groupId") Long groupId, Pageable pageable);

    @Query("SELECT m FROM Message m JOIN m.group g JOIN m.author a WHERE g.id = :groupId AND a.id = :authorId")
    Page<Message> findMessagesByGroupIdAndAuthorId(@Param("groupId") Long groupId, @Param("authorId") Long authorId, Pageable pageable);

    @Query("SELECT m FROM Message m JOIN m.group g JOIN m.author a WHERE g.id = :groupId AND a.username LIKE CONCAT('%', :author, '%')")
    Page<Message> findMessagesByGroupIdAndAuthorUsernameLike(@Param("groupId") Long groupId, @Param("author") String author, Pageable pageable);
}
