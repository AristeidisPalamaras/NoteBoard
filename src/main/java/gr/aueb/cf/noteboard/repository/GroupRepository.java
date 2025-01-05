package gr.aueb.cf.noteboard.repository;

import gr.aueb.cf.noteboard.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface GroupRepository extends JpaRepository<Group, Long>,
        JpaSpecificationExecutor<Group> {
    Optional<Group> findByName(String name);

    @Query("SELECT g FROM Group g JOIN g.owner u WHERE u.id = :ownerId")
    Set<Group> findGroupsByOwner(@Param("ownerId") Long ownerId);

    @Query("SELECT g FROM Group g JOIN g.members u WHERE u.id = :memberId")
    Set<Group> findGroupsByMember(@Param("memberId") Long memberId);
}
