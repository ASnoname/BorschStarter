package shift.borsch.services.Interfaces;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
