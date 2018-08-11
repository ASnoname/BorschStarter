package shift.borsch.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.UserInfo;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {
}