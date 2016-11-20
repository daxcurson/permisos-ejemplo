package permisos.dao;

import permisos.model.*;

public interface UserDAO {
    User findByLogin(String login);
    void save(User user);
    User findById(Long userId);
    User findByEmail(String email);   
    User findByLoginOpenId(String loginOpenId);
    User findByFacebookId(Long facebookId);
}