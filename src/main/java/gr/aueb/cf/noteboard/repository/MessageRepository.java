package gr.aueb.cf.noteboard.repository;

import gr.aueb.cf.noteboard.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long>,
        JpaSpecificationExecutor<Message> {

    @Query("SELECT m FROM Message m JOIN m.group g WHERE g.id = :groupId")
    Page<Message> findMessagesByGroup(@Param("groupId") Long groupId, Pageable pageable);

    @Query("SELECT m FROM Message m JOIN m.group g JOIN m.author a WHERE g.id = :groupId AND a.id = :authorId")
    Page<Message> findMessagesByGroupAndAuthor(@Param("groupId") Long groupId, @Param("authorId") Long authorId, Pageable pageable);
}
