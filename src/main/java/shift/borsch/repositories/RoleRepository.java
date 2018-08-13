package shift.borsch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
