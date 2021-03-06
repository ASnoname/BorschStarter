package shift.borsch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
}