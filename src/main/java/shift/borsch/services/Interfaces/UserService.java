package shift.borsch.services.Interfaces;

import shift.borsch.entities.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
