package gr.aueb.cf.noteboard.repository;

import gr.aueb.cf.noteboard.model.Group;
import gr.aueb.cf.noteboard.model.Message;
import gr.aueb.cf.noteboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Long>,
        JpaSpecificationExecutor<Message> {
    Set<Message> findAllByGroup(Group group);
    Set<Message> findAllByGroupOrderByCreatedAtDesc(Group group); //todo Do I need this, or can I order in Service layer?
    Set<Message> findAllByGroupOrderByCreatedAtAsc(Group group); //todo Do I need this, or can I order in Service layer?

    Set<Message> findAllByGroupAndAuthor(Group group, User author);
    Set<Message> findAllByGroupAndAuthorOrderByCreatedAtDesc(Group group, User author); //todo Do I need this, or can I order in Service layer?
    Set<Message> findAllByGroupAndAuthorOrderByCreatedAtAsc(Group group, User author); //todo Do I need this, or can I order in Service layer?

    @Query("SELECT m FROM Message m JOIN Views v WHERE m.group = ?1 AND v.isRead = false")
    Set<Message> findUnreadByGroup(Group group);
    @Query("SELECT m FROM Message m JOIN Views v WHERE m.group = ?1 AND v.isRead = false ORDER BY m.createdAt DESC") //todo Do I need this, or can I order in Service layer?
    Set<Message> findUnreadByGroupOrderByCreatedAtDesc(Group group);
    @Query("SELECT m FROM Message m JOIN Views v WHERE m.group = ?1 AND v.isRead = false ORDER BY m.createdAt") //todo Do I need this, or can I order in Service layer?
    Set<Message> findUnreadByGroupOrderByCreatedAtAsc(Group group);

    @Query("SELECT m FROM Message m JOIN Views v WHERE m.group = ?1 AND m.author = ?2 AND v.isRead = false")
    Set<Message> findUnreadByGroupAndAuthor(Group group, User author);
    @Query("SELECT m FROM Message m JOIN Views v WHERE m.group = ?1 AND m.author = ?2 AND v.isRead = false ORDER BY m.createdAt DESC")
    Set<Message> findUnreadByGroupAndAuthorOrderByCreatedAtDesc(Group group, User author); //todo Do I need this, or can I order in Service layer?
    @Query("SELECT m FROM Message m JOIN Views v WHERE m.group = ?1 AND m.author = ?2 AND v.isRead = false ORDER BY m.createdAt")
    Set<Message> findUnreadByGroupAndAuthorOrderByCreatedAtAsc(Group group, User author); //todo Do I need this, or can I order in Service layer?
}
