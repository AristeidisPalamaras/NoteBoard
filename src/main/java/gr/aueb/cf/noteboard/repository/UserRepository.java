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

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.joinedGroups g WHERE g.id = :groupId")
    Set<User> findUsersByGroup(@Param("groupId")Long groupId);
}
