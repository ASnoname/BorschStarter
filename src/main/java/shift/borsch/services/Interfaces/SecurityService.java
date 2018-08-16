package shift.borsch.services.Interfaces;

public interface SecurityService {

    String findLoggedInUserName();

    void autoLogin(String username, String password);
}
