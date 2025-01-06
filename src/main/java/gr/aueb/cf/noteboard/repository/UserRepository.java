package gr.aueb.cf.noteboard.repository;

import gr.aueb.cf.noteboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User> {

    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username LIKE CONCAT('%', :username, '%')")
    Set<User> findUsersByUsernameLike(@Param("username") String username);

    @Query("SELECT u FROM User u JOIN u.joinedGroups g WHERE g.id = :groupId")
    Set<User> findUsersByGroupId(@Param("groupId")Long groupId);
}
