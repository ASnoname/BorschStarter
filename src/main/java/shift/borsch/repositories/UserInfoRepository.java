package borsch.repositories;

import borsch.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {
}